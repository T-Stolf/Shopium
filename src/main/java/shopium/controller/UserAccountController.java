package shopium.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shopium.entity.*;
import shopium.adminListener.publisher.Publisher;
import shopium.assembler.*;
import shopium.authentication.CustomUserDetails;
import shopium.authentication.UserAuthentication;
import shopium.repository.*;
import shopium.exception.*;

@RestController
public class UserAccountController {

	private final UserAccountRepository repo;
	private UserAccountModelAssembler assembler;

	private UserAuthentication UAuth;

	@Autowired
	private Publisher Pub;
	
	public UserAccountController(UserAccountRepository repository, UserAccountModelAssembler ass, UserAuthentication uauth)
	{
		this.UAuth = uauth;
		this.repo = repository;
		this.assembler = ass;

	}

	// create user account
	@PostMapping("/Account/Register")
	public ResponseEntity<?> newUser(@RequestBody UserAccount newUser) throws Exception {

		System.out.println("hello");

		Pub.Event("/Account/Register");
		
		if(newUser == null)
		{
			throw new Exception("Cannot create null user");
		}

		BCryptPasswordEncoder BCPT = new BCryptPasswordEncoder();
		newUser.setDateTimeRegister(LocalDateTime.now());
		newUser.setRole("User");
		newUser.setPassword(BCPT.encode(newUser.getPassword()));

		List<UserAccount> users = repo.findAll();

		for (UserAccount user : users) {
			if (user.equals(newUser)) {
				throw new Exception("User Already exists!");
			}
			if (user.getUserName().equals(newUser.getUserName())) {
				throw new Exception("User Already exists!");
			}
		}

		EntityModel<UserAccount> entityModel = assembler.toModel(repo.save(newUser));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	// user account access
	@GetMapping("/myAccount")
	public EntityModel<UserAccount> getMyself() {

		Pub.Event("/myAccount");
		UserAccount userAccount = repo.findByUserName(UAuth.getUsername());

		return assembler.toModel(userAccount);
	}

	@PutMapping("/myAccount")
	public ResponseEntity<?> replaceMyself(@RequestBody UserAccount newUser) {

		Pub.Event("/myAccount");
		BCryptPasswordEncoder BCPT = new BCryptPasswordEncoder();
		String username = UAuth.getUsername();
		UserAccount userAccount = repo.findByUserName(username);

		userAccount.setAddress(newUser.getAddress() != null ? newUser.getAddress() : userAccount.getAddress());
		userAccount.setDateTimeRegister(newUser.getDateTimeRegister() != null ? newUser.getDateTimeRegister()
				: userAccount.getDateTimeRegister());
		userAccount.setFullName(newUser.getFullName() != null ? newUser.getFullName() : userAccount.getFullName());
		userAccount.setUserName(newUser.getUserName() != null ? newUser.getUserName() : userAccount.getUserName());
		userAccount.setPassword(
				newUser.getPassword() != null ? BCPT.encode(newUser.getPassword()) : userAccount.getPassword());

		EntityModel<UserAccount> entityModel = assembler.toModel(userAccount);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

	}

	// admin account access
	@GetMapping("/admin/userAccounts")
	public CollectionModel<EntityModel<UserAccount>> all() {

		List<EntityModel<UserAccount>> userAccounts = repo.findAll()
				.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(userAccounts, linkTo(methodOn(UserAccountController.class).all()).withSelfRel());
	}

	@PostMapping("/admin/userAccounts")
	public ResponseEntity<?> newUserAdmin(@RequestBody UserAccount newUser) {

		EntityModel<UserAccount> entityModel = assembler.toModel(repo.save(newUser));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	// Single item
	@GetMapping("/admin/userAccounts/{id}")
	public EntityModel<UserAccount> one(@PathVariable Long id) {

		UserAccount userAccount = repo.findById(id) //
				.orElseThrow(() -> new UserAccountNotFoundException(id));

		return assembler.toModel(userAccount);
	}

	@PutMapping("/admin/userAccounts/{id}")
	public ResponseEntity<?> replaceUser(@RequestBody UserAccount newUser, @PathVariable Long id) {

		UserAccount updatedUser = repo.findById(id)
				.map(user -> {
					user.setAddress(newUser.getAddress());
					user.setDateTimeRegister(newUser.getDateTimeRegister());
					user.setFullName(newUser.getFullName());
					user.setUserName(newUser.getUserName());
					return repo.save(user);
				})
				.orElseGet(() -> {
					newUser.setUserID(id);
					return repo.save(newUser);
				});

		EntityModel<UserAccount> entityModel = assembler.toModel(updatedUser);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

	}

	@DeleteMapping("/admin/userAccounts/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
