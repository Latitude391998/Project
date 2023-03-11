package project.autodelete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnusedImagesRepository extends JpaRepository<UnusedImages,Long>
{

}
