    package com.sanvalero.GestorInfo.Gestor.Repository;

    import com.sanvalero.GestorInfo.Gestor.domain.Category;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.List;


    //Hereda los métodos de las operaciones CRUD
    @Repository
    public interface CategoryRepository extends JpaRepository<Category, Long> {

        @Query("SELECT c FROM gestor_category c WHERE " +
                "(:name IS NULL OR c.name LIKE %:name%)")
        List<Category> findCategoriesByFilter(String name);
    }
