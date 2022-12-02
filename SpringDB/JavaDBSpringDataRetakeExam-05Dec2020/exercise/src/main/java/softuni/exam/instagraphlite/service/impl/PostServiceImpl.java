package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PostImportDTO;
import softuni.exam.instagraphlite.models.dtos.PostsImportDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final String path = "exercise/src/main/resources/files/posts.xml";
    private PostRepository postRepository;
    private PictureRepository pictureRepository;
    private UserRepository userRepository;
    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private ModelMapper mapper;
    private Validator validator;

    private List<Post> postsList = new ArrayList<>();

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PictureRepository pictureRepository, UserRepository userRepository) throws JAXBException {

        this.postRepository = postRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;

        this.context = JAXBContext.newInstance(PostsImportDTO.class);

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.unmarshaller = context.createUnmarshaller();

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {

        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {

        return Files.readString(Path.of(path).toAbsolutePath());
    }

    @Override
    public String importPosts() throws IOException, JAXBException {

        PostsImportDTO postsDTO = (PostsImportDTO) unmarshaller.unmarshal(new FileReader(path));

        String result = postsDTO.getPosts().stream().map(this::createResult).collect(Collectors.joining(System.lineSeparator()));

        postRepository.saveAll(postsList);

        return result;
    }

    private String createResult(PostImportDTO postDTO) {

        Set<ConstraintViolation<PostImportDTO>> validate = validator.validate(postDTO);

        if (validate.isEmpty()) {

            User user = userRepository.findByUsername(postDTO.getUser().getUsername());

            Picture picture = pictureRepository.findByPath(postDTO.getPicture().getPath());

            if (user == null || picture == null) {

                return "Invalid post";
            }

            Post post = mapper.map(postDTO, Post.class);

            post.setUser(user);
            post.setPicture(picture);

            postsList.add(post);

            return String.format("Successfully imported Post, made by %s", post.getUser().getUsername());
        } else {

            return "Invalid post";
        }
    }
}
