package com.example.football.service.impl;

import com.example.football.models.dto.BestPlayerExportDTO;
import com.example.football.models.dto.PlayerImportDTO;
import com.example.football.models.dto.PlayersImportDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.TypeMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final String path = "C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-01August2021\\skeleton\\skeleton\\src\\main\\resources\\files\\xml\\players.xml";
    private final Validator validator;
    private PlayerRepository playerRepository;
    private TownRepository townRepository;
    private StatRepository statRepository;
    private TeamRepository teamRepository;
    private ModelMapper mapper;
    private final JAXBContext context;
    private final Unmarshaller unmarshaller;
    private Map<String, Player> playersList = new LinkedHashMap<>();

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, StatRepository statRepository, TeamRepository teamRepository) throws JAXBException {

        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.teamRepository = teamRepository;

        this.mapper = new ModelMapper();

        this.context = JAXBContext.newInstance(PlayersImportDTO.class);

        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.mapper.addConverter(c -> LocalDate.parse(c.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), String.class, LocalDate.class);



    }

    @Override
    public boolean areImported() {

        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {

        return Files.readString(Path.of(path));
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {

        PlayersImportDTO playersDTO = (PlayersImportDTO) unmarshaller.unmarshal(new FileReader(path));

        String result = playersDTO.getPlayers().stream().map(this::importPlayers).collect(Collectors.joining(System.lineSeparator()));

        playerRepository.saveAll(playersList.values());

        return result;
    }

    @Override
    public String exportBestPlayers() {

        LocalDate lowerBound = LocalDate.parse("1995-01-01" );
        LocalDate upperBoundBound = LocalDate.parse("2003-01-01" );

        List<Player> players = playerRepository.findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastName(lowerBound, upperBoundBound);

        List<BestPlayerExportDTO> playersDTO = players.stream().map(this::createPlayerDTO).collect(Collectors.toList());

        String result = playersDTO.stream().map(p -> p.toString()).collect(Collectors.joining(System.lineSeparator()));

        return result;
    }

    private BestPlayerExportDTO createPlayerDTO(Player player){

        BestPlayerExportDTO model = new BestPlayerExportDTO();

        model.setFirstName(player.getFirstName());
        model.setLastName(player.getLastName());
        model.setPosition(player.getPosition().toString());
        model.setStadiumName(player.getTeam().getStadiumName());
        model.setTeamName(player.getTeam().getName());

        return model;
    }

    private String importPlayers(PlayerImportDTO playerDTO){
        Set<ConstraintViolation<PlayerImportDTO>> validate =
                validator.validate(playerDTO);

        if (validate.size() > 0) {

            return "Invalid Player";
        }

        if (playersList.containsKey(playerDTO.getEmail())) {

            return "Invalid Player";
        }

        Player player = mapper.map(playerDTO, Player.class);

        Town town  = townRepository.findByName(playerDTO.getTown().getName());

        Stat stat = statRepository.findById(playerDTO.getStat().getId()).get();

        Team team = teamRepository.findByName(playerDTO.getTeam().getName());

        player.setTown(town);
        player.setStat(stat);
        player.setTeam(team);

        playersList.put(player.getEmail(), player);

        String fullName =  player.getFirstName() + " " + player.getLastName();

        return String.format("Successfully imported Player %s - %s", fullName, player.getPosition());

    }
}
