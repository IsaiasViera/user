package com.example.users.api;

import com.example.users.model.User;
import com.example.users.service.UserService;
import com.example.users.exception.ResourceNotFoundException; // ajuste o pacote conforme necessário
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
public class UserController {
    private final UserService  userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }

@GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional<User> user = userService.getUserById(id);
        return (User) user.orElse(null);
    }
@PostMapping("/cadastro")

    public String criarUsuario(@RequestBody User user){
        return "Usuário" + user.getName() + "criado com sucesso!";
    }
}

@DeleteMapping("/deletar/{id}")
public Map<String, Boolean> deletarUsuario(@PathVariable(value = "id") Integer id) {
    Optional<User> userExistente = userService.getUserById(id);

    if (userExistente.isPresent()) {
        userService.deleteUserById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    } else {
        throw new ResourceNotFoundException("Usuário não encontrado para o ID :: " + id);
    }
}

@PutMapping("/atualizar/{id}")
public Map<String, Object> atualizarUsuario(@PathVariable(value = "id") Integer id, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {

    Optional<User> userExistente = userService.getUserById(id);

    if (!userExistente.isPresent()) {
        throw new ResourceNotFoundException("Usuário não encontrado para o ID :: " + id);
    }

    User user = userExistente.get();
    user.setName(userDetails.getName());
    user.setEmail(userDetails.getEmail());

    userService.updateUser(user);

    Map<String, Object> response = new HashMap<>();
    response.put("updated", Boolean.TRUE);
    response.put("user", user);

    return response;
}
