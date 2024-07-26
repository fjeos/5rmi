@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService UserService;

	@Autowired
	public UserController(UserService UserService) {
		this.UserService = UserService;
	}
}