package com.blinkstream.catalog.admin.application.category.create;

import com.blinkstream.catalog.admin.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateCategoryUseCaseTest {

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

        final CategoryGateway categoryGateway = mock(CategoryGateway.class);

        when(categoryGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase(categoryGateway);

        final var output = useCase.execute(aCommand);

        Assertions.assertNotNull(output);
        Assertions.assertNotNull(output.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).create(argThat(aCategory -> {
            return Objects.equals(aCategory.getName(), expectedName)
                    && Objects.equals(aCategory.getDescription(), expectedDescription)
                    && Objects.equals(aCategory.isActive(), expectedIsActive)
                    && !Objects.isNull(aCategory.getCreationDate())
                    && !Objects.isNull(aCategory.getUpdateDate())
                    && Objects.isNull(aCategory.getDeletionDate());
                }
            ));
    }
}
