package com.blinkstream.catalog.admin.domain.category;

import com.blinkstream.catalog.admin.domain.exceptions.DomainException;
import com.blinkstream.catalog.admin.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenValidParams_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Movies";
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreationDate());
        Assertions.assertNotNull(actualCategory.getUpdateDate());
        Assertions.assertNull(actualCategory.getDeletionDate());
    }

    @Test
    public void givenNullName_whenCallNewCategory_thenShouldReturnError() {
        final String expectedName = null;
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'Name' should not be null";

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenLessThan3Characters_whenCallNewCategory_thenShouldReturnError() {
        final var expectedName = "Mo ";
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'Name' must be between 3 and 255 characters";

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenMoreThan255Characters_whenCallNewCategory_thenShouldReturnError() {
        final var expectedName = """
                Considerando as lições aprendidas, a expansão dos mercados mundiais legitima a busca por soluções sistêmicas dos paradigmas corporativos. Por conseguinte, a necessidade de renovação processual garante a contribuição de um grupo importante na determinação dos aprendizados oriundos da experiência acumulada. 
                Todavia, a execução dos pontos do programa não pode mais se dissociar do remanejamento dos quadros funcionais. Neste sentido, a consolidação das estruturas requer um olhar atento sobre os desdobramentos dos compromissos firmados em instâncias multilaterais. Gostaria de enfatizar que a reformulação das abordagens metodológicas promove a alavancagem das condições financeiras e administrativas exigidas. Do ponto de vista estrutural, o julgamento imparcial das eventualidades representa uma abertura para a melhoria das diversas correntes de pensamento. No escopo da atual conjuntura, a análise aprofundada dos indicadores-chave assume importantes posições no estabelecimento dos 
                critérios de excelência amplamente aceitos.
                """;

        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'Name' must be between 3 and 255 characters";

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, ()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_shouldReturnOk(){
        final var expectedName = "Movies";
        final var expectedIsActive = true;


        final var actualCategory = Category.newCategory(expectedName, null, expectedIsActive);

        Assertions.assertDoesNotThrow(()-> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertNull(actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreationDate());
        Assertions.assertNotNull(actualCategory.getUpdateDate());
        Assertions.assertNull(actualCategory.getDeletionDate());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivateCategory_shouldReturnCategoryInactive(){
        final var expectedName = "Movies";
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, true);

        final var updatedAt = aCategory.getUpdateDate();

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        final Category actualCategory = aCategory.deactivate();

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreationDate());
        Assertions.assertNotNull(actualCategory.getUpdateDate());
        Assertions.assertNotNull(actualCategory.getDeletionDate());
        Assertions.assertTrue(aCategory.getUpdateDate().isAfter(updatedAt));
    }


    @Test
    public void givenAValidDectivedCategory_whenCallActivateCategory_shouldReturnCategoryActive(){
        final var expectedName = "Movies";
        final var expectedDescription = "Category most popular";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, false);

        final var updatedAt = aCategory.getUpdateDate();

        Assertions.assertDoesNotThrow(()-> aCategory.validate(new ThrowsValidationHandler()));

        final Category actualCategory = aCategory.activate();

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreationDate());
        Assertions.assertNotNull(actualCategory.getUpdateDate());
        Assertions.assertNull(actualCategory.getDeletionDate());
        Assertions.assertTrue(aCategory.getUpdateDate().isAfter(updatedAt));
    }

}
