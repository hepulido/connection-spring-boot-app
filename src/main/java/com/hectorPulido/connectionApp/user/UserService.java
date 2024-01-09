package com.hectorPulido.connectionApp.user;

import com.hectorPulido.connectionApp.employee.Employee;
import com.hectorPulido.connectionApp.employee.EmployeeRepository;
import com.hectorPulido.connectionApp.employer.Employer;
import com.hectorPulido.connectionApp.employer.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployerRepository employerRepository;

    @Autowired
    public UserService(UserRepository userRepository, EmployeeRepository employeeRepository, EmployerRepository employerRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;

    }

    public User signUp(UserSignUpDTO userSignUpDTO) {
        User newUser = new User();
        newUser.setFirstName(userSignUpDTO.getFirstName());
        newUser.setLastName(userSignUpDTO.getLastName());
        newUser.setEmail(userSignUpDTO.getEmail());
        newUser.setPassword(userSignUpDTO.getPassword());
        newUser.setPhoneNumber(userSignUpDTO.getPhoneNumber());
        newUser.setTypeOfUser(UserType.valueOf(userSignUpDTO.getTypeOfUser()));
        User savedUser = userRepository.save(newUser);
        if (userSignUpDTO.getTypeOfUser().equals("employee")) {
            Employee newEmployee = new Employee();
            newEmployee.setUser(savedUser);
            employeeRepository.save(newEmployee);
        } else if (userSignUpDTO.getTypeOfUser().equals("employer")) {
            Employer newEmployer = new Employer();
            newEmployer.setUser(savedUser);
            employerRepository.save(newEmployer);
        }
        return userRepository.save(newUser);
    }

    public User signIn (UserSignInDTO signInDTO) {
        Optional <User> userOptional= userRepository.findByEmailAndPassword(
                signInDTO.getEmail(),
                signInDTO.getPassword()
        );
        return userOptional.orElse(null);
    }

    public boolean updateUserProfile(Long userId, UserUpdateDTO updateDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (updateDTO.getFirstName() != null) {
                user.setFirstName(updateDTO.getFirstName());
            }
            if (updateDTO.getLastName() != null) {
                user.setLastName(updateDTO.getLastName());
            }
            if (updateDTO.getEmail() != null) {
                user.setEmail(updateDTO.getEmail());
            }
            if (updateDTO.getPassword() != null) {
                user.setPassword(updateDTO.getPassword());
            }
            if (updateDTO.getPhoneNumber() != null) {
                user.setPhoneNumber(updateDTO.getPhoneNumber());
            }
            if (updateDTO.getTypeOfUser() != null) {
                user.setTypeOfUser(UserType.valueOf(updateDTO.getTypeOfUser()));
            }

            userRepository.save(user);
            return true;

        }
        return false;
    }

    public boolean updateEmployeeProfile(Long userId, UserUpdateDTO updateDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findByUserId(userId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (updateDTO.getSkills() != null) {
                employee.setSkills(updateDTO.getSkills());
            }
            if (updateDTO.getStatus() != null) {
                employee.setStatus(updateDTO.getStatus());
            }
            employee.setYearsOfExperience(updateDTO.getYearsOfExperience());
            employee.setPayRate(updateDTO.getPayRate());
            employeeRepository.save(employee);
            return true;
        }

        return false;
    }

    public boolean updateEmployerProfile(Long userId, UserUpdateDTO updateDTO) {
        Optional<Employer> employerOptional = employerRepository.findByUserId(userId);

        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            if (updateDTO.getLocation() != null) {
                employer.setLocation(updateDTO.getLocation());
            }
            if (updateDTO.getTitle() != null) {
                employer.setTitle(updateDTO.getTitle());
            }
            if (updateDTO.getRequirements() != null) {
                employer.setRequirements(updateDTO.getRequirements());
            }
            if (updateDTO.getDescriptionOfJob() != null) {
                employer.setDescriptionOfJob(updateDTO.getDescriptionOfJob());
            }
            if (updateDTO.getLengthOfJob() != null) {
                employer.setLengthOfJob(updateDTO.getLengthOfJob());
            }
            employer.setSalary(updateDTO.getSalary());

            employerRepository.save(employer);
            return true;
        }

        return false;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public UserType getTypeOfUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getTypeOfUser();
        }
        return null;
    }
}
