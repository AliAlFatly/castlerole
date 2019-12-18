package com.example.Castlerole.service;

import java.sql.Date;
import java.util.*;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
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

        IntVector initialCoordinates = this.getXY();

        User newUser = new User(
                user.getUsername(),
                //NOTE => when using password encryption set password length way more in database since encrypting increases the size of the string.
                //https://stackoverflow.com/questions/98768/should-i-impose-a-maximum-length-on-passwords
                //set to max 128 or no max.
                //encrypt password and set it.
                bcryptEncoder.encode(user.getPassword()),
                new Date(System.currentTimeMillis()),
                initialCoordinates.getFirst(),
                initialCoordinates.getSecond(),
                "player",
                300,
                300,
                300,
                300,
                300
        );

        return userRepository.save(newUser);
    }

    public IntVector getXY() throws Exception {

        Integer finalXCoordinate;
        Integer finalYCoordinate;


        //initialize 2 empty vector lists.
        //save all taken spots in vectorList
        ArrayList<Vector> vectorArrayList = new ArrayList<>();
        //save all empty spots in targetList
        ArrayList<Vector> targetVectorArrayList = new ArrayList<>();
        var users = userRepository.findAll();
        var nodes = nodeRepository.findAll();
        //add user and node spawns to "spot taken arrayList"
        for (User user: users) {
            vectorArrayList.add(new Vector(user.getxCoordinate(), user.getyCoordinate()));
        }
        for (Node node: nodes) {
            vectorArrayList.add(new Vector(node.getxCoordinate(), node.getyCoordinate()));
        }
        //sort the list => x then y
        VectorSorting(vectorArrayList);

        //loop over every possible x/y coordinate
        for (int x = 0; x < gridSize; x++){
            for (int y = 0; y < gridSize; y++){
                //if spot taken list is not empty check condition
                if (!vectorArrayList.isEmpty()){
                    //if the current loop coordinate is same as the first coordinate in the sorted spot taken list, remove it from the spot taken list and continue
                    if (vectorArrayList.get(0).getX() == x && vectorArrayList.get(0).getY() == y ){
                        vectorArrayList.remove(0);
                    }
                    //else add the coordinate(which is not in the takenList) to the targetList)
                    else {
                        targetVectorArrayList.add(new Vector(x,y));
                    }
                }
                //since spot taken list is empty can safely add all remaining possible coordinates.
                else {
                    targetVectorArrayList.add(new Vector(x,y));
                }
            }
        }
        Random r = new Random();
        //if the targetList is not empty that means there are spots left.
        if (!targetVectorArrayList.isEmpty()){
            Integer lastIndex = targetVectorArrayList.size();
            //chose index randomly between first and last index.
            //NOTE: r.next returns 0 - (size-1), size returns total number of elements so if array got 10 elements then it returns 10, last index = 9. so r.nextInt returns between 0-(10-1) => between 0-9.
            Integer chosenIndex = r.nextInt(lastIndex);
            //assign the values of the randomly chosen index to the return variables
            finalXCoordinate = targetVectorArrayList.get(chosenIndex).getX();
            finalYCoordinate = targetVectorArrayList.get(chosenIndex).getY();
        }
        else {
            //if targetList is empty, no more grids
            throw new Exception("No more grid spots left");
        }
        //return chosen index values.
        return new IntVector(finalXCoordinate, finalYCoordinate);
    }


    public static void VectorSorting(ArrayList<Vector> arrayList){
        //sort X
        for (int i = 0; i < arrayList.size();i++){
            var key = arrayList.get(i);
            var j = i - 1;
            while (j > -1 && arrayList.get(j).getX() > key.getX()){
                arrayList.set(j+1, arrayList.get(j));
                j--;
            }
            arrayList.set(j+1,key);
        }
        //sort Y (in the X sortedList)
        for (int y = 0; y < arrayList.size(); y++){
            var key = arrayList.get(y);
            var j = y - 1;
            while (j > -1
                    && arrayList.get(j).getX() == key.getX()
                    && arrayList.get(j).getY() > key.getY())
            {
                arrayList.set(j+1, arrayList.get(j));
                j--;
            }
            arrayList.set(j+1,key);
        }
    }

    //todo: remove????
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