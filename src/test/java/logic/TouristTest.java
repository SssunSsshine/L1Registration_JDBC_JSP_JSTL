package logic;


import com.vsu.entities.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class TouristTest {

    private final UserRepository touristRepository = new UserRepository(new ConnectionFactory());

    @Test
    public void getDoesNotThrowException() throws SQLException {
        User tourist = touristRepository.selectById(1L);
        Assertions.assertDoesNotThrow(() -> touristRepository.selectById(1L));
    }
    @Test
    public void selectDoesNotThrowException() throws SQLException {
        User tourist = touristRepository.selectByEmail("mmm@ddd.ru");
        Assertions.assertDoesNotThrow(() -> touristRepository.selectById(1L));
    }
  /*  @Test
    public void insertDoesNotThrowExceptionWithNullPatronymicNull() throws SQLException {
        if (touristRepository.get(ALEX_TOURIST.getPhone()) != null){
            touristRepository.deleteByPhone(TestData.ALEX_TOURIST.getPhone());
        }

        Assertions.assertDoesNotThrow(() -> touristRepository.insert(TestData.ALEX_TOURIST));
    }

    @Test
    public void insertDoesNotThrowExceptionWithNullEmail() throws SQLException {
        if (touristRepository.get(JAMES_TOURIST.getPhone()) != null){
            touristRepository.deleteByPhone(TestData.JAMES_TOURIST.getPhone());
        }

        Assertions.assertDoesNotThrow(() -> touristRepository.insert(TestData.JAMES_TOURIST));
    }

    @Test
    public void touristIsInserted() throws SQLException {
        if (touristRepository.get(ALEX_TOURIST.getPhone()) != null){
            touristRepository.deleteByPhone(TestData.ALEX_TOURIST.getPhone());
        }

        touristRepository.insert(TestData.ALEX_TOURIST);
        Tourist touristAfter = touristRepository.get(ALEX_TOURIST.getPhone());

        touristRepository.deleteByPhone(TestData.ALEX_TOURIST.getPhone());

        Assertions.assertNotEquals(null, touristAfter);
    }
    @Test
    public void touristsAreSelected() throws SQLException {
       List<Tourist> tourists = touristRepository.getAll();

        Assertions.assertNotEquals(null, tourists);
    }
    @Test
    public void deleteDoesNotThrowException() throws SQLException {
        if (touristRepository.get(ALEX_TOURIST.getPhone()) == null){
            touristRepository.insert(ALEX_TOURIST);
        }

        Assertions.assertDoesNotThrow(() -> touristRepository.deleteByPhone(ALEX_TOURIST.getPhone()));
    }

    @Test
    public void touristIsDeleted() throws SQLException {
        Tourist tourist;
        if (touristRepository.get(ALEX_TOURIST.getPhone()) == null){
            touristRepository.insert(ALEX_TOURIST);
        }

        try{
            touristRepository.deleteByPhone(ALEX_TOURIST.getPhone());
        }catch (NullPointerException e){
            System.out.println(e);
            return;
        }

        tourist = touristRepository.get(ALEX_TOURIST.getPhone());
        touristRepository.insert(ALEX_TOURIST);

        Assertions.assertNull(tourist);
    }

    @Test
    public void deleteByIDDoesNotThrowException() throws SQLException {
        Long id1 = 76L;
        Long id2 = 75L;
        Long id3 = 74L;
        touristRepository.deleteById(id2);
        touristRepository.deleteById(id3);
        Assertions.assertDoesNotThrow(() -> touristRepository.deleteById(id1));
    }

    @Test
    public void updateDoesNotThrowException() throws SQLException {
        Tourist mamedov = touristRepository.get(ID_MAMEDOV);
        mamedov.setName("sheesh");
        Assertions.assertDoesNotThrow(()->touristRepository.updateByID(mamedov));
    }

    @Test
    public void touristIsUpdated() throws SQLException {
        Tourist mamedov = touristRepository.get(ID_MAMEDOV);
        mamedov.setName("Илья");
        touristRepository.updateByID(mamedov);
        String nameBefore = touristRepository.get(ID_MAMEDOV).getName();

        mamedov = touristRepository.get(ID_MAMEDOV);
        mamedov.setName("Sheesh");
        touristRepository.updateByID(mamedov);
        String nameAfter = touristRepository.get(ID_MAMEDOV).getName();

        Assertions.assertNotEquals(nameBefore, nameAfter);
    }*/
}
