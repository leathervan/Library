package service;

import dao.user.UserDao;
import entity.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        userService = new UserService(userDao);
    }

    @Test
    public void createUserTest() {
        User user = new User(1,"1snizinka@gmail.com","1snizinka2002","1Bogdan","1Oskin",1);

        userService.signUp(user);

        verify(userDao, times(1)).create(user);
    }
    @Test
    public void getUserByCredentialsTest() {
        User user = new User(1,"1snizinka@gmail.com","1snizinka2002","1Bogdan","1Oskin",1);

        when(userDao.getUserByLoginAndPassword(user.getEmail(),user.getPassword())).thenReturn(user);

        User result = userService.getUserByCredentials(user.getEmail(),user.getPassword());

        assertEquals(result, user);
        verify(userDao, times(1)).getUserByLoginAndPassword(user.getEmail(),user.getPassword());
    }

    @Test
    public void getUserById(){
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"1snizinka@gmail.com","1snizinka2002","1Bogdan","1Oskin",1));
        list.add(new User(2,"2snizinka@gmail.com","2snizinka2002","2Bogdan","2Oskin",2));
        list.add(new User(3,"3snizinka@gmail.com","3snizinka2002","3Bogdan","3Oskin",3));

        when(userDao.get(1)).thenReturn(list.get(0));

        User result = userService.getUserById(1);

        assertEquals(result,list.get(0));

        verify(userDao, times(1)).get(1);
    }

    @Test
    public void getAllUsersTest() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1,"1snizinka@gmail.com","1snizinka2002","1Bogdan","1Oskin",1));
        list.add(new User(2,"2snizinka@gmail.com","2snizinka2002","2Bogdan","2Oskin",2));
        list.add(new User(3,"3snizinka@gmail.com","3snizinka2002","3Bogdan","3Oskin",3));

        when(userDao.getAll()).thenReturn(list);

        List<User> empList = userService.getAllUsers();

        assertEquals(3, empList.size());
        verify(userDao, times(1)).getAll();
    }

}