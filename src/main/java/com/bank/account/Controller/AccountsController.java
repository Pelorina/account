package com.bank.account.Controller;

import com.bank.account.Constants.AccountConstants;
import com.bank.account.Dto.CustomerDto;
import com.bank.account.Dto.ResponseDto;
import com.bank.account.Service.Impl.AccountsServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class AccountsController {
    private AccountsServiceImpl accountsService;

    //    @Operation(
//            summary = "Create Account REST API",
//            description = "REST API to create new Customer &  Account inside EazyBank"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "201",
//                    description = "HTTP Status CREATED"
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "HTTP Status Internal Server Error",
//                    content = @Content(
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    }
//    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode(AccountConstants.STATUS_201)
                        .statusMsg(AccountConstants.MESSAGE_201)
                        .build());
    }

    //    @Operation(
//            summary = "Fetch Account Details REST API",
//            description = "REST API to fetch Customer &  Account details based on a mobile number"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "HTTP Status OK"
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "HTTP Status Internal Server Error",
//                    content = @Content(
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    }
//    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    //    @Operation(
//            summary = "Update Account Details REST API",
//            description = "REST API to update Customer &  Account details based on a account number"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "HTTP Status OK"
//            ),
//            @ApiResponse(
//                    responseCode = "417",
//                    description = "Expectation Failed"
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "HTTP Status Internal Server Error",
//                    content = @Content(
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    }
//    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode(AccountConstants.STATUS_200)
                            .statusMsg(AccountConstants.STATUS_200)
                            .build());

        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode(AccountConstants.STATUS_417)
                            .statusMsg(AccountConstants.STATUS_417)
                            .build());
        }
    }

    //    @Operation(
//            summary = "Delete Account & Customer Details REST API",
//            description = "REST API to delete Customer &  Account details based on a mobile number"
//    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "HTTP Status OK"
//            ),
//            @ApiResponse(
//                    responseCode = "417",
//                    description = "Expectation Failed"
//            ),
//            @ApiResponse(
//                    responseCode = "500",
//                    description = "HTTP Status Internal Server Error",
//                    content = @Content(
//                            schema = @Schema(implementation = ErrorResponseDto.class)
//                    )
//            )
//    }
//    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));


        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode(AccountConstants.STATUS_417)
                            .statusMsg(AccountConstants.STATUS_417)
                            .build());
        }
    }
}
