# Validation Starter 
Utility validator for msisdn, iin, bin, imei, imeiSV

## Getting Started

### Install it to the corporate artifactory

Just add it as dependency to your **build.gradle** (or **build.gradle.kts**)
<br/><b>old gradle version</b>
```groovy
repositories {
    maven {
        url = '${ARTIFACTORY_URL}'
    }
}
dependencies {
    compile 'kz.progger.starter:spring-boot-starter-validator:0.0.3'
}
```

**new version**
```groovy
repositories {
    maven {
        url = 'http://artifactory.kcell.kz/libs-release-local'
    }
}
dependencies {
    implementation 'kz.progger.starter:spring-boot-starter-validator:0.0.3'
}
```

### Usage

Add needed annotation on top of your variable.

We've created a simple class with one field to apply the validation rules. In example, we’re setting up our annotated field to be validated(on every example)

##### Msisdn

Validation steps of msisdn
* Msisdn is not null
* All value is number
* Msisdn range between 7_000_000_0000L and 7_999_999_9999L

Example:

Java:
```groovy
@Msisdn
String msisdn;

```

Kotlin:
```groovy
@field:Msisdn
val msisdn: String;

```
##### Bin

Validation steps of msisdn
* Bin is not null
* Bin length is 12
* All value is number
* The fifth number of bin have to be 4,5,6
* Checking by the algorithm of BIN(read more [here](https://ru.wikipedia.org/wiki/%D0%91%D0%B8%D0%B7%D0%BD%D0%B5%D1%81-%D0%B8%D0%B4%D0%B5%D0%BD%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B9_%D0%BD%D0%BE%D0%BC%D0%B5%D1%80))

Example:

Java
```groovy
@Bin
String bin;

```


Kotlin:
```groovy
@field:Bin
val bin: String;

```

##### Iin

Validation steps of msisdn
* Iin is not null
* Iin length is 12
* All value is number
* The fifth number of iin have to be 0,1,2,3
* Checking by the algorithm of IIN(read more [here](Imei ru.wikipedia.org/wiki/Индивидуальный_идентификационный_номер))

Example:

Java
```groovy
@Iin
String iin;

```

Kotlin
```groovy
@field:Iin
val iin: String;

```

##### Imei and ImeiSV

The SV Stands for software version. If you need an IMEI for some reason, stick with the first 14 digits, include the 15th if exchanging the number with someone so they can check you didn’t have a typo. ([read more here](https://www.cspsprotocol.com/imei/))

Validation steps of IMEI
* IMEI is not null
* IMEI length is 14
* All value is number
<br />

Example:

Java
```groovy

@Imei
String imei;

```

Kotlin
```groovy

@field:Imei
val imei: String;

```

Validation steps of ImeiSV
* ImeiSV is not null
* ImeiSV length is 15
* All value is number
* ImeiSV validation by the [Luna Algorithm](https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%9B%D1%83%D0%BD%D0%B0)

Example:

Java
```groovy

@ImeiSV
String imeiSV;

```

Kotlin
```groovy

@field:ImeiSV
val imeiSV: String;

```

## Important 

To validate the object or some value dont forget to annotate with **<i>@Valid</i>**

On Kotlin you have to override method handleMethodArgumentNotValid. On any error occured on step of validating request parameters @Valid catches MethodArgumentNotValid exception and sends 400 BadRequest without errorMessage. That is why you have to add it on ControllerAdvice class.  
```groovy
@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() { 

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders?,
                                              status: HttpStatus?,
                                              request: WebRequest?): ResponseEntity<Any> {
        val validationList = ex.bindingResult.fieldErrors.stream().map { fieldError: FieldError -> fieldError.defaultMessage }.collect(Collectors.toList())
        val apiErrorVO = ApiError(validationList)
        apiErrorVO.errorList = validationList
        return ResponseEntity(apiErrorVO, status)
    }
}
```

## Example

To validate the model in Spring MVC, let's create a controller with a /user POST mapping that receives a NewUserForm object annotated with @Valid and verifies whether there are any validation errors:

```groovy

@Controller
public class NewUserController {
 
    @GetMapping("/user")
    public String loadFormPage(Model model) {
        model.addAttribute("newUserForm", new NewUserForm());
        return "userHome";
    }
 
    @PostMapping("/user")
    public String submitForm(@Valid NewUserForm newUserForm, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userHome";
        }
        model.addAttribute("message", "Valid form");
        return "userHome";
    }
}
```


