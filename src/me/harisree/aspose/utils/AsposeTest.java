package me.harisree.aspose.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aspose.tasks.Asn;
import com.aspose.tasks.ChildTasksCollector;
import com.aspose.tasks.License;
import com.aspose.tasks.Prj;
import com.aspose.tasks.Project;
import com.aspose.tasks.Resource;
import com.aspose.tasks.ResourceAssignment;
import com.aspose.tasks.ResourceAssignmentCollection;
import com.aspose.tasks.Rsc;
import com.aspose.tasks.SaveFileFormat;
import com.aspose.tasks.Task;
import com.aspose.tasks.TaskUtils;
import com.aspose.tasks.Tsk;


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
		
		for (Task task : getTasksForBiweeklyUpdate(project)) {
			
			//System.out.println("Task Name: " + task.get(Tsk.NAME));
			
			for(ResourceAssignment assignment : getResourcesForTask(task)) {
				
				System.out.println("Task Name: " + task.get(Tsk.NAME) + "[" + assignment.get(Asn.UNITS) +" " +assignment.get(Rsc.MATERIAL_LABEL)+ "]");
				
			}

		}
		
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
	
	public static List<Task> getTasksForBiweeklyUpdate(Project project) {
		
		ChildTasksCollector collector = new ChildTasksCollector();
		
		TaskUtils.apply(project.getRootTask(), collector, 0);
		
		return collector.getTasks();
		
	}
	
	public static List<ResourceAssignment> getResourcesForTask(Task task) {
		
		List<ResourceAssignment> resourceList = new ArrayList<>();
		
		for(ResourceAssignment ass : task.getAssignments()){
			
			resourceList.add(ass);
		
		}
		
		return resourceList;
		
	}

}
