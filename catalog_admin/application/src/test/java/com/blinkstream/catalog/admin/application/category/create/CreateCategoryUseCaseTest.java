package com.blinkstream.catalog.admin.application.category.create;

import com.blinkstream.catalog.admin.domain.category.CategoryGateway;
import com.blinkstream.catalog.admin.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {
    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;
    @Mock
    private CategoryGateway categoryGateway;

    //1. Caminho perfeito
    //2. Propriedade inválida
    //3. Caminho semi=perfeito
    //4. Erro generico do gateway

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId(){
        final var expectedName = "Movies";
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;


        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);


        when(this.categoryGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var output = useCase.execute(aCommand);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(this.categoryGateway, Mockito.times(1)).create(argThat(aCategory -> {
            return Objects.equals(aCategory.getName(), expectedName)
                    && Objects.equals(aCategory.getDescription(), expectedDescription)
                    && Objects.equals(aCategory.isActive(), expectedIsActive)
                    && !Objects.isNull(aCategory.getCreationDate())
                    && !Objects.isNull(aCategory.getUpdateDate())
                    && Objects.isNull(aCategory.getDeletionDate());
                }
            ));
    }


    @Test
    public void givenAInvalidCommand_whenCallsCreateCategory_shouldReturnDomainException(){
        final String expectedMessage = "'Name' should not be null";
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;


        final var aCommand = CreateCategoryCommand.with(null, expectedDescription, expectedIsActive);
        
        final var expectedAssertion =  Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedMessage, expectedAssertion.getErrors().get(0).message());

        Mockito.verify(this.categoryGateway, Mockito.times(0)).create(any());
    }
}
