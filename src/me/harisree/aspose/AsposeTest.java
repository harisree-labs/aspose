package me.harisree.aspose;

import java.io.File;

import com.aspose.tasks.License;
import com.aspose.tasks.Prj;
import com.aspose.tasks.Project;
import com.aspose.tasks.SaveFileFormat;

public class AsposeTest {
	
	private static final String LICENSE_PATH = "/home/u1188/ddfs/aspose/Aspose.Tasks.lic";
	private static final String HOME_DIRECTORY = "/home/u1188/ddfs/aspose/";
	private static final String SAMPLE_MPP = "project3.mpp";
	
	
	public static void main(String[] args) {
		
		System.out.println("Playing with $999 :P - (Aspose)");
		
		addLicense(LICENSE_PATH);
		
		System.out.println("License Added");
		
		Project project = openProject(HOME_DIRECTORY, SAMPLE_MPP);
		
		System.out.println("Calendar : " + project.get(Prj.AUTHOR));
		
		//project.set(Prj.AUTHOR,"Sreehari B S");
		
		//System.out.println("Calendar : " + project.get(Prj.AUTHOR));
		
		//project.save(HOME_DIRECTORY + "project3.mpp", SaveFileFormat.MPP);
		
	}
	
	public static void addLicense(String filePath) {
		
		License asposeLicense = new License();
		
		File licenseFile = new File(filePath);
		
		try {
			
			asposeLicense.setLicense(licenseFile);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static Project openProject(String homeDir , String fileName) {
		
		Project existingProject = null;
		
		try {
			
			existingProject = new Project(homeDir + fileName);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		return existingProject;
		
	}
	
	public static void saveAsMpp(Project project, String newFileName) {
		
		project.save(HOME_DIRECTORY + newFileName, SaveFileFormat.MPP);
		
	}

}
