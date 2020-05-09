package ch.excape.ynabclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class CategoryGroupWithCategoriesWrapper {

    @JsonProperty("category_groups")
    private List<CategoryGroupWithCategories> categoryGroupWithCategoriesList;

    public CategoryGroupWithCategoriesWrapper categoryGroupWithCategoriesList(List<CategoryGroupWithCategories> categoryGroupWithCategories) {
        this.categoryGroupWithCategoriesList = categoryGroupWithCategories;
        return this;
    }

    public List<CategoryGroupWithCategories> getCategoryGroupWithCategoriesList() {
        return categoryGroupWithCategoriesList;
    }

    public void setCategoryGroupWithCategoriesList(List<CategoryGroupWithCategories> categoryGroupWithCategoriesList) {
        this.categoryGroupWithCategoriesList = categoryGroupWithCategoriesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryGroupWithCategoriesWrapper that = (CategoryGroupWithCategoriesWrapper) o;
        return Objects.equals(getCategoryGroupWithCategoriesList(), that.getCategoryGroupWithCategoriesList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryGroupWithCategoriesList());
    }

    @Override
    public String toString() {
        return "CategoryGroupWithCategoriesWrapper{" +
                "categoryGroupWithCategoriesList=" + categoryGroupWithCategoriesList +
                '}';
    }
}
