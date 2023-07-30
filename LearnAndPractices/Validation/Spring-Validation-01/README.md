# Validation Day 01

#Requirements

- JDK 11
- lombok
- spring-boot-starter-validation

#Steps to take

- Create new normal project Spring Boot

- Update pom.xml

  ```
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.1</version>
      <scope>test</scope>
  </dependency>
  ```

- Create package outside @SpringBootApplication

  com.example.springvalidation01.SpringValidation01Application (include @SpringBootApplication)

  com.example.testcommonvalidation (new package created)

- Start with new package (com.example.testcommonvalidation)

  xxx.enity

  ```
  @Getter
  @Setter
  public class ErrorResponse {
      private String message;
      private String errorTypeName;
      
      public ErrorResponse(Exception e) {
          this(e.getClass().getName(), e.getMessage());
      }
      
      public ErrorResponse(String errorTypeName, String message) {
          this.message = message;
          this.errorTypeName = errorTypeName;
      }
  }
  ```

​	xxx.exception

(GlobalExceptionhandler.java)

```
@ResponseBody
@ControllerAdvice(assignableTypes = { ExceptionController.class })
public class GlobalExceptionHandler {
    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException("Error Parameter"));
    ErrorResponse resourceNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("Sorry, the resource not found!"));

    @ExceptionHandler(value = Exception.class)  // handle all exception
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(400).body(illegalArgumentResponse);
        } else if (e instanceof ResourceNotFoundException) {
            return ResponseEntity.status(404).body(resourceNotFoundResponse);
        }
        return null;
    }
}
```

=======================================================================================================

@ResponseBody: is an annotation in Spring Framework used to mark a method or a class to return data from the application's Controller as an HTTP Response body instead of returning an HTML page.

When a method or class is marked with @ResponseBody, Spring converts the returned data into an HTTP Response object.
The data can be a Java object, a list, a text string, or any other data type. Spring uses an HttpMessageConverter
to convert the data into a format that matches the client's requirements, such as JSON or XML.

For example, if a method in the Controller is marked with @ResponseBody and returns a User object, Spring will convert the User object to JSON (or XML) and send it as an HTTP Response back to the client.
@RestController
public class UserController {

     @GetMapping("/user")
     @ResponseBody
     public User getUser() {
         User user = new User("John", "john@example.com");
         return user;
     }
}
When the client calls /user, Spring will generate an HTTP Response containing the User's information in the form of JSON as follows:

{
   "name": "John",
   "email": "john@example.com"
}
@ResponseBody is useful when you want to return data other than HTML, such as returning JSON for front-end applications or APIs.

-------------------

In Spring Framework, the @ControllerAdvice annotation is used to handle global exceptions for Controllers in the application. When an exception occurs in a Controller,
Spring will look for exception handling methods in beans marked with @ControllerAdvice to handle that exception.
However, there may be multiple @ControllerAdvice in the application and you want to explicitly specify to Spring that only a specific @ControllerAdvice should be used to handle exceptions in a particular group of Controllers.

In the code @ControllerAdvice(assignableTypes = {ExceptionController.class}), we have an additional assignableTypes property whose value is an array of classes.
This attribute helps to explicitly specify that @ControllerAdvice should be applied to Controllers of the ExceptionController class.

For example, suppose you have a Controller named ExceptionController:

@Controller
public class ExceptionController {

     @GetMapping("/error")
     public void throwException() {
         throw new RuntimeException("Sample Exception");
     }
     // Other controller methods...
}
You want to use @ControllerAdvice to explicitly specify to Spring that only @ControllerAdvice with something like the following will handle exceptions that occur in the ExceptionController:

@ControllerAdvice(assignableTypes = {ExceptionController.class})
public class GlobalExceptionHandler {

     @ExceptionHandler(RuntimeException.class)
     public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
         return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
In this case, when you access the /error path and call the throwException() method in the ExceptionController,
a RuntimeException will be thrown and the GlobalExceptionHandler will handle that exception and return an HTTP Response with status code 500 and the message "An unexpected error occurred".

=======================================================================================================

- Create 

  ```
  @Getter
  @Setter
  public class ResourceNotFoundException extends RuntimeException {
      private String message;
  
      public ResourceNotFoundException() {}
  
      public ResourceNotFoundException(String message) {
          super(message);
          this.message = message;
      }
  }
  ```

- Create (com.example.testcommonvalidation.controller)

  ```
  @RestController
  @RequestMapping("/api")
  public class ExceptionController {
  
      @GetMapping("/illegalArgumentException")
      public void throwIllegalArgumentException() {
          throw new IllegalArgumentException();
      }
  
      @GetMapping("/resourceNotFoundException")
      public void throwResourceNotFoundException() {
          throw new ResourceNotFoundException();
      }
  }
  ```

- Create test package

  ```
  @SpringBootTest(classes = SpringValidation01Application.class)  // suggests this test is part of Spring Boot, because it is outside @SpringBootApplication
  @AutoConfigureMockMvc
  class ExceptionControllerTest {
  
      @Autowired private MockMvc mockMvc;
  
      @Test
      void throwIllegalArgumentException() throws Exception {
          mockMvc.perform(get("/api/illegalArgumentException"))
                  .andExpect(status().is(400))
                  .andExpect(jsonPath("$.message").value("Error Parameter"));
      }
  
      @Test
      void throwResourceNotFoundException() throws Exception {
          mockMvc.perform(get("/api/resourceNotFoundException"))
                  .andExpect(status().is(404))
                  .andExpect(jsonPath("$.message").value("Sorry, the resource not found!"));
      }
  }
  ```

Attention

```
// java main
@SpringBootApplication
@ComponentScan({ "com.example.testcommonvalidation" }) // outside package @SpringBootApplication
// test package
@SpringBootTest(classes = SpringValidation01Application.class)  // suggests this test is part of Spring Boot,
```

### **Use @ControllerAdvice to specify specific Exception handling at the Controller, this way is no longer used much**.

```
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseStatusExceptionCustomHandle extends RuntimeException{
    public ResponseStatusExceptionCustomHandle() {}

    public ResponseStatusExceptionCustomHandle(String message) {
        super(message);
    }
}
```

```
@RestController
@RequestMapping("/api")
public class ResponseStatusExceptionCustomController {

    @GetMapping("/responseStatusExceptionCustomController")
    public void throwResponseStatusExceptionCustomController() {
        throw new ResponseStatusExceptionCustomHandle("Sorry, the resource not found!");
    }

    @GetMapping("/responseStatusExceptionCustomController2")
    public void throwResponseStatusExceptionCustomController2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, the resource not found!");
    }
}
```

throwResponseStatusExceptionCustomController and throwResponseStatusExceptionCustomController2 same output.

```
{
    "timestamp": "2023-08-21T07:28:12.017+0000",
        "status": 404,
        "error": "Not Found",
        "message": "Sorry, the resourse not found!",
        "path": "/api/resourceNotFoundException3"
}
```

**ResponseStatusException** provider

```
public ResponseStatusException(HttpStatus status) {
   this(status, null);
}

public ResponseStatusException(HttpStatus status, @Nullable String reason) {
	super("");
	Assert.notNull(status, "HttpStatus is required");
	this.status = status.value();
	this.reason = reason;
}

public ResponseStatusException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause) {
	super(null, cause);
	Assert.notNull(status, "HttpStatus is required");
	this.status = status.value();
	this.reason = reason;
}
```

The parameters in the constructor are explained as follows:

• status: http status
• reason: message content of response
• cause : the exception thrown

## Validation Day 02

- Create new package (outside @SpringBootApplication) ==> **com.example.validationday02**

- Create ==> com.example.validationday02.entity

  ```
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class PersonRequest {
      @NotNull(message = "classId not blank")
      private String classId;
  
      @Size(max = 33)
      @NotNull(message = "name not blank")
      private String name;
  
      @Pattern(regexp = "(^Man$|^Woman$|^UGM$)", message = "sex the value is not in the optional range")
      @NotNull(message = "sex not blank")
      private String sex;
  }
  ```

- Create ==> com.example.validationday02.exception

  ```
  @ControllerAdvice(assignableTypes = PersonController.class)
  public class GlobalExceptionHandlerDay02 {
  
      @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
          Map<String, String> errors = new HashMap<>();
          ex.getBindingResult().getAllErrors().forEach(error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
          });
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
      }
  
      @ExceptionHandler(ConstraintViolationException.class)
      public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
      }
  }
  ```

- Create ==> com.example.validationday02.controller

  ```
  @RestController
  @RequestMapping("api/persons")
  public class PersonController {
      @PostMapping
      public ResponseEntity<PersonRequest> save(@RequestBody @Valid PersonRequest personRequest) {
          return ResponseEntity.ok().body(personRequest);
      }
  
      @GetMapping
      public ResponseEntity<Integer> getPersonById(@Valid @PathVariable("id") @Max(value = 5, message = "Exceeded the range of id") Integer id) {
          return ResponseEntity.ok().body(id);
      }
  
      @PutMapping
      public ResponseEntity<String> getPersonByName(@Valid @RequestParam("name") @Size(max = 6, message = "Exceeded the range of name") String name) {
          return ResponseEntity.ok().body(name);
      }
  }
  ```

- Create test

  ```
  @SpringBootTest(classes = SpringValidation01Application.class)
  @AutoConfigureMockMvc
  class PersonControllerTest {
  
      @Autowired private MockMvc mockMvc;
      @Autowired private ObjectMapper objectMapper;
  
      @Test
      public void save() throws Exception {
          PersonRequest personRequest = PersonRequest.builder().sex("Man22").classId("12345678").build();
          mockMvc.perform(post("/api/persons")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(personRequest)))
           .andExpect(MockMvcResultMatchers.jsonPath("sex").value("sex the value is not in the optional range"))
           .andExpect(MockMvcResultMatchers.jsonPath("name").value("name not blank"));
      }
  }
  ```

![image-20230730144627840](C:\Users\PC\AppData\Roaming\Typora\typora-user-images\image-20230730144627840.png)

- Update Controller

  ```
  @RestController
  @RequestMapping("api/persons")
  @Validated
  public class PersonController {
  	/// ...
  	@GetMapping("/{id}")
      public ResponseEntity<Integer> getPersonById(
      					@Valid @PathVariable("id") 
      					@Max(value = 5, message = "Exceeded the range of id") Integer id) {
          return ResponseEntity.ok().body(id);
      }
  }
  ```

- Update test

  ```
  @Test
  void getPersonById() throws Exception {
      mockMvc.perform(get("/api/persons/6")
                      .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isBadRequest())
              .andExpect(content().string("getPersonById.id: Exceeded the range of id"));
  }
  
  @Test
  void getPersonByName() throws Exception {
      mockMvc.perform(put("/api/persons")
                      .param("name", "minhminhminh")
                      .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isBadRequest())
              .andExpect(content().string("getPersonByName.name: Exceeded the range of name"));
  }
  ```

----------

## **Attention: Also you can validation at Service level, instead of Controller.**

- Create package service (com.example.validationday02.service)

  ```
  @Service
  @Validated
  public class PersonService {
  
      public void validatePersonRequest(@Valid PersonRequest personRequest) {
          // do something
      }
  }
  ```

- Create test

   

  ```
  @SpringBootTest(classes = SpringValidation01Application.class)
  class PersonServiceTest {
      @Autowired private PersonService personService;
  
      @Test
      void validatePersonRequest() {
          try {
              PersonRequest personRequest = PersonRequest.builder()
                      .sex("Man22").classId("12345678").build();
              personService.validatePersonRequest(personRequest);
          } catch (ConstraintViolationException exception) {
              exception.getConstraintViolations().forEach(constraintViolation ->
                      System.out.println(constraintViolation.getMessage())
              );
          }
  }
  ```

	Output console: 
	name not blank
	sex the value is not in the optional range

- modify PersonServiceTest

  ```
  @SpringBootTest(classes = SpringValidation01Application.class)
  class PersonServiceTest {
  
      @Autowired private PersonService personService;
      @Autowired private Validator validator;
  
      @Test
      public void validatePersonRequest() {
          try {
              PersonRequest personRequest = PersonRequest.builder()
                      .sex("Man22").classId("12345678").build();
              personService.validatePersonRequest(personRequest);
          } catch (ConstraintViolationException exception) {
              exception.getConstraintViolations().forEach(constraintViolation ->
                      System.out.println(constraintViolation.getMessage())
              );
          }
      }
  
      @Test
      public void checkPersonManually() {
          ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
          Validator validator = validatorFactory.getValidator();
          PersonRequest personRequest = PersonRequest.builder()
                  .sex("Man22").classId("12345678").build();
          Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
          violations.forEach(constraintViolation ->
                  System.out.println(constraintViolation.getMessage())
          );
      }
  
      @Test
      public void checkPersonManually2() {
          PersonRequest personRequest = PersonRequest.builder()
                  .sex("Man22").classId("12345678").build();
          Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
          violations.forEach(constraintViolation ->
                  System.out.println(constraintViolation.getMessage())
          );
      }
  }
  ```

*In some scenarios, we may need to manually verify and obtain the verification results.*

*The `Validator` example we get via the `Validator` factory class. In addition, if it is in Spring Bean, it can also be injected directly through `@Autowired`.*

----

***Custom Validation***

If the built-in validation annotations cannot meet your needs, you can also customize the implementation annotations.

Case 1: Check whether the value of a specific field is in the optional range

- Create annotaion Region

  ```
  @Target(ElementType.FIELD)
  @Documented
  @Retention(RetentionPolicy.RUNTIME)
  @Constraint(validatedBy = RegionValidator.class)
  public @interface Region {
      String message() default "Region value is not in optional range";
      Class<?>[] groups() default {};
      Class<? extends Payload>[] payload() default {};
  }
  ```

***Explain:***

*The above code defines a custom annotation named @Region. This annotation will be used to apply custom constraints on fields in Java class.*

*Let's dive into each element of the @Region annotation:*

*@Target(ElementType.FIELD): @Target is a target annotation (meta-annotation) in Java. In this case, the @Region annotation can only be applied on the fields of the Java class. This ensures that only fields can be marked with @Region.*

*@Documented: @Documented is a target annotation (meta-annotation) in Java. It is used to ensure that the information about the @Region annotation will be included in the API documentation when it is created.*

*@Retention(RetentionPolicy.RUNTIME): @Retention is a retention annotation (meta-annotation) in Java. In this case, the @Region annotation will be retained and available at runtime of the application. This allows us to use the @Region annotation during the execution of the application.*

*@Constraint(validatedBy = RegionValidator.class): @Constraint is not an annotation in Java core, but it is an annotation of the Bean Validation API (JSR-380). In this case, the @Region annotation is marked with @Constraint, specifies that it should apply custom constraints, and defines validation logic using the Validator RegionValidator class.*

*Then, in the body of the @Region annotation, we define the components as follows:*

*message(): This is a parameterless method that defines a default error message if the value is invalid. In this case, the default message is "Region value is not in optional range".*

*groups(): This is a method that returns an array of Classes<?> (group classes), allowing you to define binding groups. In this case, no group is defined by default, so the empty array is the default value.*

*payload(): This is a method that returns an array of Class<? extends Payload> (classes load) and allows you to attach extra information (payload) to the constraint. In this case, no payload is default defined, so the empty array is the default value.*

*In a nutshell, the @Region annotation is a custom annotation used to define custom constraints for fields in a Java class. When applied, it will execute validation logic through the Validator RegionValidator class, and can provide a default error message if the value is invalid.*

- You need to implement the `ConstraintValidator` interface and override the `isValid` method.

  ```
  public class RegionValidator implements ConstraintValidator<Region, String> {
      @Override
      public boolean isValid(String value, ConstraintValidatorContext context) {
          HashSet<Object> regions = new HashSet<>();
          regions.add("VN");
          regions.add("VN-Thai");
          regions.add("VN-Lao");
          return regions.contains(value);
      }
  }
  ```

***Explain***:

The above code defines a custom class named RegionValidator to perform a validation check for the value to which the @Region annotation is applied. The RegionValidator class implements the ConstraintValidator<Region, String> interface, where Region is the custom @Region annotation it will check, and String is the data type of the value to which the @Region annotation is applied.

Let's dive into the isValid() method in the RegionValidator class:

public boolean isValid(String value, ConstraintValidatorContext context): This is a method that implements from the ConstraintValidator interface. This method will perform a validation check for the value to which the @Region annotation is applied. In this case, value is a String (data type defined in ConstraintValidator).

return regions.contains(value);: In isValid() method, it checks if the value is in HashSet regions using contains() method. If the value is in regions, the method returns true, which is a valid value. Otherwise, it will return false, i.e. invalid value.

When you use the @Region annotation and the field's value is checked through the RegionValidator, the isValid() method will be called to check the validity of the value. In this example, if the field's value is "VN", "VN-Thai", or "VN-Lao", it will be considered valid. If the value is not "VN", "VN-Thai", or "VN-Lao", it will be invalid and return an error message.

- Update test

  ```
  @Test
  public void testSavePersonRegionCustomAnnotationValidation() throws Exception {
      PersonRequest personRequest = PersonRequest.builder()
              .region("VNI").build();
      mockMvc.perform(post("/api/persons")
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(objectMapper.writeValueAsString(personRequest)))
         .andExpect(MockMvcResultMatchers.jsonPath("region").value("Region value is not in optional range"));
  }
  
  @Test
  public void testValidationRequest() throws Exception {
      PersonRequest personRequest = PersonRequest.builder()
              .sex("Man22").classId("1234567890").region("Bali").build();
      mockMvc.perform(post("/api/persons")
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(objectMapper.writeValueAsString(personRequest)))
         .andExpect(MockMvcResultMatchers.jsonPath("region").value("Region value is not in optional range"))
         .andExpect(MockMvcResultMatchers.jsonPath("sex").value("sex the value is not in the optional range"))
         .andExpect(MockMvcResultMatchers.jsonPath("name").value("name not blank"));
  }
  ```

  ```
  @Test
  public void testPersonRegionByCustomAnnotationValidation() {
      PersonRequest personRequest = PersonRequest.builder()
              .sex("Man").classId("12345678").region("VNI").build();
      Set<ConstraintViolation<PersonRequest>> violations = validator.validate(personRequest);
      violations.forEach(constraintViolation ->
              System.out.println(constraintViolation.getMessage())
      );
  }
  ```

-----

```
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumber {
    String message() default "Invalid phone number";
    Class[] groups() default {};
    Class[] payload() default {};
}
```

```
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)  return true;
        String regex = "(84|0[3|5|7|8|9])([0-9]{8})\\b";
        return value.matches(regex);
    }
}
```

```
@Test
public void testValidationPhoneNumber() throws Exception {
    PersonRequest personRequest = PersonRequest.builder()
            .phoneNumber("9841234567").build();
    mockMvc.perform(post("/api/persons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(personRequest)))
    .andExpect(MockMvcResultMatchers.jsonPath("phoneNumber").value("phone number is not in the correct format"));
}
```

```
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotNull(message = "classId not blank")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name not blank")
    private String name;

    @Pattern(regexp = "(^Man$|^Woman$|^UGM$)", message = "sex the value is not in the optional range")
    @NotNull(message = "sex not blank")
    private String sex;

    @Region
    private String region;

    @PhoneNumber(message = "phone number is not in the correct format")
    @NotNull(message = "phone not blank")
    private String phoneNumber;
}
```

---

There are also validation groups, but this is not recommended because it makes it difficult and complicated to develop the code.

```
@Data
public class Person {
    // When the verification group is DeletePersonGroup, the group field cannot be empty
    @NotNull(groups = DeletePersonGroup.class)
    // When the verification group is AddPersonGroup, the group field cannot be empty
    @NotNull(groups = AddPersonGroup.class)
    private String group;
}
```

```
public interface AddPersonGroup {
}

public interface DeletePersonGroup {
}
```

```
@Service
@Validated
public class PersonService {
    public void validatePersonRequest(@Valid PersonRequest personRequest) {
        // do something
    }

    @Validated(AddPersonGroup.class)
    public void validatePersonGroupForAdd(@Valid Person person) {
        // do something
    }

    @Validated(DeletePersonGroup.class)
    public void validatePersonGroupForDelete(@Valid Person person) {
        // do something
    }
}
```

```
@Test
public void shouldCheckPersonWithGroups() {
    Person person = new Person();
    person.setGroup(null);
    personService.validatePersonGroupForAdd(person);
}

@Test
public void shouldCheckPersonWithGroups2() {
    Person person = new Person();
    personService.validatePersonGroupForDelete(person);
}
```

## ***The experience of the verification group after using it is a bit of an anti-pattern feeling, which makes the maintainability of the code worse! Try not to use it!***

----

## Summary of commonly used validation annotations

`JSR303` defines the standard `validation-api` for `Bean Validation` (validation), and does not provide an implementation. `Hibernate Validation` is the implementation `hibernate-validator` of this specification/specification, and adds annotations such as `@Email`, `@Length`, `@Range`, etc. The underlying dependency of `Spring Validation` is `Hibernate Validation`.

**Validation annotations provided by JSR**:

- `@Null` annotated elements must be null
- `@NotNull` annotated elements must not be null
- `@AssertTrue` annotated elements must be true
- `@AssertFalse` annotated elements must be false
- `@Min(value)` the annotated element must be a number whose value must be greater than or equal to the specified minimum value
- `@Max(value)` the annotated element must be a number whose value must be less than or equal to the specified maximum value
- `@DecimalMin(value)` the annotated element must be a number whose value must be greater than or equal to the specified minimum value
- `@DecimalMax(value)` the annotated element must be a number whose value must be less than or equal to the specified maximum value
- `@Size(max=, min=)` the size of the annotated element must be within the specified range
- `@Digits (integer, fraction)` the annotated element must be a number whose value must be within an acceptable range
- `@Past` annotated elements must be a past date
- `@Future` annotated elements must be a future date
- `@Pattern(regex=,flag=)` annotated elements must match the specified regular expression

**Validation annotations provided by Hibernate Validator**:

- `@NotBlank(message =)` verifies that the string is not null and must have a length greater than 0
- `@Email` annotated elements must be email addresses
- `@Length(min=,max=)` the size of the annotated string must be within the specified range
- `@NotEmpty` must be non-empty for the annotated string
- `@Range(min=,max=,message=)` annotated elements must be in the appropriate range

Expand 

Friends often ask: "What is the difference between `@NotNull` and `@Column(nullable = false)`?" 

Let me answer briefly here: 

- `@NotNull` is a JSR 303 Bean Validation annotation, which has nothing to do with database constraints themselves. 

- `@Column(nullable = false)` : is the JPA method to declare the column to be non-null. 

In summary, the former is used for verification, while the latter is used to indicate the constraints on the table when the database creates the table.
