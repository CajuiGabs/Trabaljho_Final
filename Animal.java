import java.time.LocalDate;
import java.util.List;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String especie;

    private String raca;

    private String sexo;

    private LocalDate dataNascimento;

    private String cor;

    private String observacoes;

    // Getters e setters
    public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        Animal existingAnimal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found: " + id));
        existingAnimal.setNome(animal.getNome());
        existingAnimal.setEspecie(animal.getEspecie());
        existingAnimal.setRaca(animal.getRaca());
        existingAnimal.setSexo(animal.getSexo());
        existingAnimal.setDataNascimento(animal.getDataNascimento());
        existingAnimal.setCor(animal.getCor());
        existingAnimal.setObservacoes(animal.getObservacoes());
        return animalRepository.save(existingAnimal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}

}
