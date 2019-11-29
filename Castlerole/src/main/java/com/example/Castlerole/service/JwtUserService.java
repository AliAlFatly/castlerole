package com.example.Castlerole.service;

import java.sql.Array;
import java.sql.Date;
import java.util.*;

import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class JwtUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Value("${gridSize}")
    private int gridSize;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }



    public User registerNewUser(UserDTO user) throws Exception {
        //get UserDTO
        //initialize new empty user(to save in the database)

        int finalXCoordinate;
        int finalYCoordinate;

        ArrayList<Integer> xList = new ArrayList<Integer>();
        ArrayList<Integer> yList = new ArrayList<Integer>();
        ArrayList<Integer> targetXList = new ArrayList<Integer>();
        ArrayList<Integer> targetYList = new ArrayList<Integer>();

        userRepository.findAll().forEach(x -> xList.add(x.getxCoordinate()));
        nodeRepository.findAll().forEach(x -> xList.add(x.getxCoordinate()));
        InsertionSorting(xList);

        userRepository.findAll().forEach(y -> yList.add(y.getyCoordinate()));
        nodeRepository.findAll().forEach(y -> yList.add(y.getyCoordinate()));
        InsertionSorting(yList);


        for (int i = 0; i < gridSize; i++){
            if (!xList.isEmpty()){
                if (xList.get(0) == i){
                    xList.remove(0);
                }
                else {
                    targetXList.add(i);
                }
            }
            else{
                targetXList.add(i);
            }

            if (!yList.isEmpty()){
                if (yList.get(0) == i){
                    yList.remove(0);
                }
                else {
                    targetYList.add(i);
                }
            }
            else {
                targetYList.add(i);
            }
        }

        Random r = new Random();

        if (targetXList != null){
            Integer xMin = Collections.min(targetXList);
            Integer xMax = Collections.max(targetXList);
            Integer xCoordinates = (r.nextInt(xMax - xMin) + xMin);
            finalXCoordinate = xCoordinates;
        }
        else {
            throw new Exception("No more grid spots left for a new castle");
        }
        if (targetYList != null){
            Integer yMin = Collections.min(targetYList);
            Integer yMax = Collections.max(targetYList);
            Integer yCoordinates = (r.nextInt(yMax - yMin) + yMin);
            finalYCoordinate = yCoordinates;
        }
        else {
            throw new Exception("No more grid spots left for a new castle");
        }

        User newUser = new User();
        //set username as given in dto
        newUser.setUsername(user.getUsername());
        //NOTE => when using password encryption set password length way more in database since encrypting increases the size of the string.
        //https://stackoverflow.com/questions/98768/should-i-impose-a-maximum-length-on-passwords
        //set to max 128 or no max.
        //encrypt password and set it.
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setJoinDate(new Date(System.currentTimeMillis()));
        newUser.setXCoordinate(finalXCoordinate);
        newUser.setYCoordinate(finalYCoordinate);
        newUser.setWood(0);
        newUser.setIron(0);
        newUser.setStone(0);
        newUser.setFood(0);
        newUser.setTroops(0);
        return userRepository.save(newUser);
    }

    public static void InsertionSorting(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++){
            var key = arrayList.get(i);
            var j = i - 1;
            while( j > -1 && arrayList.get(j) > key){
                arrayList.set(j+1, arrayList.get(j));
                j--;
            }
            arrayList.set(j+1, key);
        }
    }
}