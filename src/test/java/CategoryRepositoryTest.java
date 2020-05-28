import com.placementsOfGoods.model.Category;
import com.placementsOfGoods.repository.CategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    private final String categoryName = "testCategory";

    private Category getTestCategoryData() {
        Category category = new Category();
        category.setName(categoryName);
        return category;
    }

    @Test
    void saveCategory(){
        Category inputCategory = getTestCategoryData();
        CategoryRepository.saveCategory(inputCategory);
        Category expected = CategoryRepository.getCategoryByName(categoryName);
        assertNotEquals(null, expected);
        assertEquals(categoryName, expected.getName());
        CategoryRepository.deleteCategory(categoryName);
    }

    @Test
    void testDeleteCategory(){
        Category inputCategory = getTestCategoryData();
        CategoryRepository.saveCategory(inputCategory);
        Category expected = CategoryRepository.getCategoryByName(categoryName);
        assertNotEquals(null, expected);
        assertEquals(categoryName, expected.getName());
        CategoryRepository.deleteCategory(categoryName);
        try {
            CategoryRepository.getCategoryByName(categoryName);
        }catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    void testUpdateCategory(){
        Category inputCategory = getTestCategoryData();
        CategoryRepository.saveCategory(inputCategory);
        Category expected = CategoryRepository.getCategoryByName(categoryName);
        final String updatedName = "updatedName";
        expected.setName(updatedName);
        CategoryRepository.updateCategory(expected.getId(), updatedName);
        Category expectedAfterUpdate = CategoryRepository.getCategoryByName(updatedName);
        assertNotNull(expectedAfterUpdate);
        assertEquals(expectedAfterUpdate.getId(), expected.getId());
        CategoryRepository.deleteCategory(updatedName);
    }

    @Test
    void testGetAllCategories(){
        int count = CategoryRepository.getCategories().size();
        Category inputCategory = getTestCategoryData();
        CategoryRepository.saveCategory(inputCategory);
        Category anotherCategory = new Category();
        anotherCategory.setName("anotherCategory");
        CategoryRepository.saveCategory(anotherCategory);
        List<Category> categories = CategoryRepository.getCategories();
        assertEquals(count+2, categories.size());
        assertEquals(categories.get(count).getName(), inputCategory.getName());
        CategoryRepository.deleteCategory(categoryName);
        CategoryRepository.deleteCategory(anotherCategory.getName());
    }

}
