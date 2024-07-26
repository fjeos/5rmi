@Service
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository) userRepository) {
		this.userRepository = userRepository;
	}
}