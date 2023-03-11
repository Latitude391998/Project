package project.autodelete;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AutoDelete 
{
	List<UnusedImages> unusedImages = new ArrayList<>();
	
	@Autowired
	private UnusedImagesRepository unusedImagesRepo;
	
	@Scheduled(fixedRate=21600000)  // function will invoke once in 6 hours
	public void autoDelete()
	{
		System.out.println("autodelete run");
		unusedImages=unusedImagesRepo.findAll();
		
		for (int i=0; i<unusedImages.size(); i++)
		{
			UnusedImages unusedImageObj = unusedImages.get(i);
			String pathOfFile = unusedImageObj.getPath();
			File fileToDelete = new File(pathOfFile);
			boolean isDeleted = fileToDelete.delete();
			if (isDeleted == true)
			{
				unusedImagesRepo.delete(unusedImageObj);
			}
		}
		System.out.println(unusedImages);
	}
}
