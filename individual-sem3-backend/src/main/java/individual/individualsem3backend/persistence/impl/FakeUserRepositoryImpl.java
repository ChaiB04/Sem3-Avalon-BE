package individual.individualsem3backend.persistence.impl;

import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.persistence.UserRepository;
import individual.individualsem3backend.persistence.exception.DatabaseException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {

    private static int NEXT_ID = 1;

    private List<User> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    public void deleteById(int userId) {
        this.savedUsers.removeIf(user -> user.getId() == userId);
    }

    public User findUserById(int userId){
          Optional<User> getUser = this.savedUsers
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst();

          if(getUser != null){
              return getUser.get();
          }
          else{
              throw new DatabaseException("User not found.");
          }
    }
    public void update(User user){
        try{
            savedUsers.set(user.getId() -1 , user);
        }
        catch(Exception ex){
            throw new DatabaseException("User could not be updated.");
        }

    }
    public User findByEmail(String email) {
        try{
            return this.savedUsers
                    .stream()
                    .filter(user -> user.getEmail() == email)
                    .findFirst()
                    .orElse(null);
        }
        catch(Exception ex){
            throw new DatabaseException("User could not be found by email.");
        }
    }

    public User findByEmailAndPassword(String email, String password){
      try{
          return this.savedUsers
                  .stream()
                  .filter(user -> user.getEmail() == email && user.getPassword() == password)
                  .findFirst()
                  .orElse(null);
      }
      catch(Exception ex){
          throw new DatabaseException("User could not be found by credentials.");
      }
    }

    public User save(User user) {
        try{
            user.setId(NEXT_ID);
            NEXT_ID++;
            this.savedUsers.add(user);
            return user;
        }
        catch(Exception ex)
        {
            throw new DatabaseException("Could not save new user.");
        }
    }
}
