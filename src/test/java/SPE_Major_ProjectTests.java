import com.example.SPE_Major_project.Dto.UserDto;
import com.example.SPE_Major_project.Entity.User;
import com.example.SPE_Major_project.Repository.AuthenticationRepository;
import com.example.SPE_Major_project.Service.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class SPE_Major_ProjectTests {

        @Mock
        private AuthenticationRepository authenticationRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private AuthenticationServiceImpl authenticationService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void loginUser_withCorrectCredentials_shouldReturnUser() {
            // given
            UserDto userDto = new UserDto();
            userDto.setEmail("test@example.com");
            userDto.setPassword("password");

            User user = User.builder()
                    .userId(1)
                    .firstName("John")
                    .lastName("Doe")
                    .email("test@example.com")
                    .password("password")
                    .build();

            when(authenticationRepository.findByEmail(anyString())).thenReturn(user);
            when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

            // when
            User result = authenticationService.loginUser(userDto);

            // then
            assertNotNull(result);
            assertEquals(user.getUserId(), result.getUserId());
            assertEquals(user.getFirstName(), result.getFirstName());
            assertEquals(user.getLastName(), result.getLastName());
            assertEquals(user.getEmail(), result.getEmail());
        }

        @Test
        void loginUser_withIncorrectCredentials_shouldReturnNull() {
            // given
            UserDto userDto = new UserDto();
            userDto.setEmail("test@example.com");
            userDto.setPassword("wrongPassword");

            User user = User.builder()
                    .userId(1)
                    .firstName("John")
                    .lastName("Doe")
                    .email("test@example.com")
                    .password("password")
                    .build();

            when(authenticationRepository.findByEmail(anyString())).thenReturn(user);
            when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

            // when
            User result = authenticationService.loginUser(userDto);

            // then
            assertNull(result);
        }

        @Test
        void loginUser_withNonexistentUser_shouldReturnNull() {
            // given
            UserDto userDto = new UserDto();
            userDto.setEmail("test@example.com");
            userDto.setPassword("password");

            when(authenticationRepository.findByEmail(anyString())).thenReturn(null);

            // when
            User result = authenticationService.loginUser(userDto);

            // then
            assertNull(result);
        }
    }

