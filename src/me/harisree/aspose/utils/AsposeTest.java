package me.harisree.aspose.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.aspose.tasks.Asn;
import com.aspose.tasks.ChildTasksCollector;
import com.aspose.tasks.License;
import com.aspose.tasks.Prj;
import com.aspose.tasks.Project;
import com.aspose.tasks.ProjectView;
import com.aspose.tasks.Resource;
import com.aspose.tasks.ResourceAssignment;
import com.aspose.tasks.ResourceAssignmentCollection;
import com.aspose.tasks.Rsc;
import com.aspose.tasks.SaveFileFormat;
import com.aspose.tasks.Task;
import com.aspose.tasks.TaskUsageView;
import com.aspose.tasks.TaskUtils;
import com.aspose.tasks.TimeUnitType;
import com.aspose.tasks.TimephasedData;
import com.aspose.tasks.TimephasedDataCollection;
import com.aspose.tasks.TimephasedDataType;
import com.aspose.tasks.Tsk;
import com.aspose.tasks.WorkContourType;


public class AsposeTest {
	
	private static final String LICENSE_PATH = "/home/u1188/ddfs/aspose/Aspose.Tasks.lic";
	private static final String HOME_DIRECTORY = "/home/u1188/ddfs/aspose/";
	private static final String SAMPLE_MPP = "Project1_2007.mpp";
	
	
	public static void main(String[] args) {
		
		System.out.println("Playing with $999 :P - (Aspose)");
		
		addLicense(LICENSE_PATH);
		
		System.out.println("License Added");
		
		Project project = openProject(HOME_DIRECTORY, SAMPLE_MPP);
		
		ChildTasksCollector collector = new ChildTasksCollector();
		TaskUtils.apply(project.getRootTask(),collector,0);
		List<Task> tasks =  collector.getTasks();
		for(Task tsk : tasks)
		{
			//System.out.println(tsk.get(Tsk.NAME));
			
		    /*if(tsk.get(Tsk.UID) == 2 ) {
		    	tsk.set(Tsk.PERCENT_COMPLETE, 40);
		    }*/
			
			System.out.println(tsk.toString());
			
			for(ResourceAssignment assign : tsk.getAssignments()) {
				
				System.out.println(assign.get(Asn.UID));
				
			}
			
		}
		
/*		for(ResourceAssignment assn :  project.getResourceAssignments()) {
			
			System.out.println(assn.toString());
			
		}*/
		
		ResourceAssignment assn =  project.getResourceAssignments().getByUid(18);
		
		assn.set(Asn.WORK_CONTOUR,WorkContourType.Contoured);

		TimephasedData td = new TimephasedData();
		td.setUid(18);
	    td.setStart(assn.get(Asn.START));
	    td.setFinish(new Date(assn.get(Asn.START).getTime()+86400000));
	    td.setValue("2");
	    td.setUnit(TimeUnitType.Hour);
	    td.setTimephasedDataType(TimephasedDataType.AssignmentRemainingWork);
	    assn.getTimephasedData().add(td);
		
		project.recalculate();
		project.save(HOME_DIRECTORY+"Project1_2007_out.mpp", SaveFileFormat.MPP);
		
		System.out.println("Done");
		
		
		//saveAsMpp(project, "project_updated.mpp");
		
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
		
		project.save(HOME_DIRECTORY + newFileName, SaveFileFormat.XML);
		
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
