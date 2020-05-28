import com.placementsOfGoods.model.User;
import com.placementsOfGoods.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {

    private final String email = "test@gmail.com";
    private final String password = "password";

    private User getTestUserData() {
        User inputUser = new User();
        inputUser.setEmail(email);
        inputUser.setName("testName");
        inputUser.setSurname("testSurname");
        inputUser.setPassword(password);
        return inputUser;
    }

    @Test
    void testSaveUser() {
        User inputUser = getTestUserData();
        UserRepository.saveUser(inputUser);
        List<User> expectedUsers = UserRepository.getLoginedUser(email, password);
        assertEquals(1, expectedUsers.size());
        User expectedUser = expectedUsers.get(0);
        assertEquals(expectedUser.getEmail(), inputUser.getEmail());
        assertEquals(expectedUser.getSurname(), inputUser.getSurname());
        assertEquals(expectedUser.getName(), inputUser.getName());
        UserRepository.deleteUser(expectedUser.getId());
    }

    @Test
    void testDeleteUser() {
        User inputUser = getTestUserData();
        UserRepository.saveUser(inputUser);
        User expectedUser = UserRepository.getLoginedUser(email, password).get(0);
        Integer id = expectedUser.getId();
        UserRepository.deleteUser(id);
        List<User> expectedUsers = UserRepository.getLoginedUser(email, password);
        assertEquals(0, expectedUsers.size());
    }

    @Test
    void testGetAllUsers() {
        int count = UserRepository.getUsers().size();
        User inputUser = getTestUserData();
        UserRepository.saveUser(inputUser);
        User secondUser = new User();
        secondUser.setEmail(email+"2");
        secondUser.setName("testName2");
        secondUser.setSurname("testSurname2");
        secondUser.setPassword(password+"2");
        UserRepository.saveUser(secondUser);
        List<User> expectedUsers = UserRepository.getUsers();
        assertEquals(count+2, expectedUsers.size());
        assertEquals(email, expectedUsers.get(0+count).getEmail());
        assertEquals(password, expectedUsers.get(0+count).getPassword());
        assertEquals(email+"2", expectedUsers.get(1+count).getEmail());
        assertEquals(password+"2", expectedUsers.get(1+count).getPassword());
        UserRepository.deleteUser(expectedUsers.get(0+count).getId());
        UserRepository.deleteUser(expectedUsers.get(1+count).getId());
    }

    @Test
    void testUpdateUser(){
        User inputUser = getTestUserData();
        UserRepository.saveUser(inputUser);
        User expectedUser = UserRepository.getLoginedUser(email, password).get(0);
        inputUser.setName("newName");
        inputUser.setPassword("newPassword");
        UserRepository.updateUser(expectedUser.getId(), inputUser);
        List<User> expectedUserAfterUpdate = UserRepository.getLoginedUser(email, "newPassword");
        assertEquals(1, expectedUserAfterUpdate.size());
        assertEquals("newName", expectedUserAfterUpdate.get(0).getName());
        assertEquals(inputUser.getSurname(), expectedUserAfterUpdate.get(0).getSurname());
        UserRepository.deleteUser(expectedUserAfterUpdate.get(0).getId());
    }

}
