    package com.sanvalero.GestorInfo.Gestor.Controller;

    import com.sanvalero.GestorInfo.Gestor.Service.UserService;
    import com.sanvalero.GestorInfo.Gestor.domain.User;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {

        private static final Logger logger = LoggerFactory.getLogger(UserController.class);
        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping
        public ResponseEntity<List<User>> getFilteredUsers(
                @RequestParam(name = "fullname", required = false) String fullName,
                @RequestParam(name = "username", required = false) String username,
                @RequestParam(name = "email", required = false) String email) {

            logger.debug("Fetching filtered users with parameters: fullname={}, username={}, email={}",
                    fullName, username, email);

            List<User> filteredUsers = userService.getFilteredUsers(fullName, username, email);
            return new ResponseEntity<>(filteredUsers, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            logger.debug("Fetching user with ID: {}", id);

            User user = userService.getUserById(id).orElse(null);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            logger.debug("Creating user: {}", user);

            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        }

        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
            logger.debug("Updating user with ID: {}. New data: {}", id, updatedUser);

            User user = userService.updateUser(id, updatedUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable Long id) {
            logger.debug("Deleting user with ID: {}", id);

            userService.deleteUser(id);
            String message = "Usuario con ID " + id + " borrado exitosamente.";
            return ResponseEntity.ok(message);
        }
    }
