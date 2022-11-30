package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AgentServiceImpl implements AgentService {

    private AgentRepository agentRepository;

    private TownRepository townRepository;
    private Validator validator;
    private ModelMapper mapper;
    private Gson gson;
    private Map<String, Agent> agentsList = new LinkedHashMap<>();

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {

        this.agentRepository = agentRepository;

        this.townRepository = townRepository;

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.gson = new Gson();

        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {

        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {

        Path path = Path.of("C:\\SoftUni\\SpringDB\\JavaDBSpringDataExam-02April2022\\skeleton\\src\\main\\resources\\files\\json\\agents.json");

        return Files.readString(path);
    }

    @Override
    public String importAgents() throws IOException {

        String json = readAgentsFromFile();

        AgentImportDTO[] agentsDTO = gson.fromJson(json, AgentImportDTO[].class);

        StringBuilder result = createResult(agentsDTO);

        agentRepository.saveAll(agentsList.values());

        return result.toString();
    }

    private StringBuilder createResult(AgentImportDTO[] agentsDTO) {

        StringBuilder sb = new StringBuilder();

        for (AgentImportDTO agentDTO : agentsDTO) {

            Set<ConstraintViolation<AgentImportDTO>> validate = validator.validate(agentDTO);

            if (validate.isEmpty()) {

                if (agentsList.containsKey(agentDTO.getFirstName())) {

                    sb.append("Invalid agent");
                } else {

                    Town town = townRepository.findByTownName(agentDTO.getTown());

                    Agent agent = mapper.map(agentDTO, Agent.class);

                    agent.setTown(town);

                    agentsList.put(agent.getFirstName(), agent);

                    sb.append(String.format("Successfully imported agent - %s %s", agent.getFirstName(), agent.getLastName()));
                }

            } else {

                sb.append("Invalid agent");
            }

            sb.append(System.lineSeparator());
        }

        return sb;
    }

}
