    package com.sanvalero.GestorInfo.Gestor.Repository;

    import com.sanvalero.GestorInfo.Gestor.domain.Publication;
    import org.springframework.data.jpa.repository.JpaRepository;

    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;


    @Repository
    public interface PublicationRepository extends JpaRepository<Publication, Long> {

        @Query("SELECT p FROM gestor_publication p WHERE " +
                "(:title IS NULL OR p.title LIKE %:title%) AND " +
                "(:user IS NULL OR p.user.username LIKE %:user%) AND " +
                "(:category IS NULL OR p.category.name LIKE %:category%)")
        List<Publication> findPublicationsByFilter(
                @Param("title") String title,
                @Param("user") String user,
                @Param("category") String category);
    }
