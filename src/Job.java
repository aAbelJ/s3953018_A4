import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Job implements Serializable {
    private String jobNumber;
    private String jobTitle;
    private String jobPosterName;
    private String jobPosterAddress;
    private String jobPostedDate;
    private String jobExperienceLevel;
    private String jobType;
    private String [] jobRequiredSkills;
    private double jobSalary;
    private String jobDescription;

    /**
     * Constructor
     * @param number - set jobName
     * @param title - set jobTitle
     * @param posterName - set jobPosterName
     * @param posterAddress - set jobPosterAddress
     * @param date - set jobPostedDate
     * @param experienceLevel - set jobExperienceLevel
     * @param type - set jobType
     * @param skills - set jobRequiredSkills
     * @param salary - set jobSalary
     * @param description - set jobDescription
     */
    public Job (String number, String title, String posterName, String posterAddress, String date,
                String experienceLevel, String type, String [] skills, double salary, String description) {
        jobNumber = number;
        jobTitle = title;
        jobPosterName = posterName;
        jobPosterAddress = posterAddress;
        jobPostedDate = date;
        jobExperienceLevel = experienceLevel;
        jobType = type;
        jobRequiredSkills = skills;
        jobSalary = salary;
        jobDescription = description;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Job.txt"));
        String [] array = {"AA AAA", "AA AA", "dddd s"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 40000, "Description example" );
        Job jobUpdate = new Job("13232DEF$", "Job22", "Doe2", "Sydney,Ne3w South Wales,Australia", "2023-02-21", "Junior", "Full time", array, 40000, "Description example" );
        //If addJob true then serialize job object to txt file
        if (job.addJob(job)) {
            System.out.println("added job");
            out.writeObject(job);
            out.close();
        }

        //if updateJob true then change job values to update job values
        //serialize job object with new update job values
        if (job.updateJob(jobUpdate)) {
            System.out.println("updated job");
            job.jobNumber = jobUpdate.jobNumber;
            job.jobTitle = jobUpdate.jobTitle;
            job.jobPosterName = jobUpdate.jobPosterName;
            job.jobPosterAddress = jobUpdate.jobPosterAddress;
            job.jobPostedDate = jobUpdate.jobPostedDate;
            job.jobExperienceLevel = jobUpdate.jobExperienceLevel;
            job.jobType = jobUpdate.jobType;
            job.jobRequiredSkills = jobUpdate.jobRequiredSkills;
            job.jobSalary = jobUpdate.jobSalary;
            job.jobDescription = jobUpdate.jobDescription;

            ObjectOutputStream outOverwrite = new ObjectOutputStream(new FileOutputStream("Job.txt"));
            outOverwrite.writeObject(job);
            outOverwrite.close();
        }
    }

    /**
     * adds job to text file if return true
     * @param job input parameter job object to test validate values
     * @return boolean
     */
    public boolean addJob(Job job) {
        //check if job number is null or the length is not 9
        if (job.jobNumber == null || job.jobNumber.length() != 9) {
            return false;
        }

        //check if first 5 values of job number does not match 1-5, values 6-8 does not match A-Z
        //and last value is not a special character
        if(!(job.jobNumber.substring(0,4).matches("[1-5]+")) || !(job.jobNumber.substring(5,7).matches("[A-Z]+")) || !(job.jobNumber.substring(job.jobNumber.length() -1).matches("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]"))){
            return false;
        }
        //check if date not match date format
        if (!(job.jobPostedDate.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"))) {
            return false;
        }

        //check if date not match address format
        if (!(job.jobPosterAddress.matches("^[^,]+,[^,]+,[^,]+$"))) {
            return false;
        }

        //check if senior or executives get paid under 100000
        if (job.jobExperienceLevel.equals("Senior") || job.jobExperienceLevel.equals("Executive")) {
            if (job.jobSalary < 100000) {
                return false;
            }
        }
        //check if junior gets paid under 40000 or over 70000
        if (job.jobExperienceLevel.equals("Junior")) {
            if (job.jobSalary < 40000 || job.jobSalary > 70000) {
                return false;
            }
        }
        //check if senior or executive are part-timers
        if (job.jobType.equals("Part time") && (job.jobExperienceLevel.equals("Senior") || job.jobExperienceLevel.equals("Executive"))) {
            return false;
        }
        //check if there is more than 3 skills
        if (job.jobRequiredSkills.length > 3 ) {
            return false;
        }
        //check if job skill has two words
        for (int i = 0; i < job.jobRequiredSkills.length; i++) {
            String[] check = job.jobRequiredSkills[i].split(" ");
            if (check.length > 2) {
                return  false;
            }
        }

        return true;
    }

    /**
     * Deserialize object in txt file to compare
     * Validates new update values
     * @param jobUpdate - updated job values
     * @return - boolean
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public boolean updateJob(Job jobUpdate) throws IOException, ClassNotFoundException {
        Job job = null;
        //Deserialize txt file
        FileInputStream fileIn = new FileInputStream("Job.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        job = (Job) in.readObject();

        fileIn.close();
        in.close();
        //check if job number is null or the length is not 9
        if (jobUpdate.jobNumber == null || jobUpdate.jobNumber.length() != 9) {
            return false;
        }
        //check if first 5 values of job number does not match 1-5, values 6-8 does not match A-Z
        //and last value is not a special character
        if(!(jobUpdate.jobNumber.substring(0,4).matches("[1-5]+")) || !(jobUpdate.jobNumber.substring(5,7).matches("[A-Z]+")) || !(jobUpdate.jobNumber.substring(jobUpdate.jobNumber.length() -1).matches("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]"))){
            return false;
        }
        //check if date not match date format
        if (!(jobUpdate.jobPostedDate.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"))) {
            return false;
        }
        //check if date not match address format
        if (!(jobUpdate.jobPosterAddress.matches("^[^,]+,[^,]+,[^,]+$"))) {
            return false;
        }
        //check if senior or executives get paid under 100000
        if (jobUpdate.jobExperienceLevel.equals("Senior") || jobUpdate.jobExperienceLevel.equals("Executive")) {
            if (jobUpdate.jobSalary < 100000) {
                return false;
            }
        }
        //check if junior gets paid under 40000 or over 70000
        if (jobUpdate.jobExperienceLevel.equals("Junior")) {
            if (jobUpdate.jobSalary < 40000 || jobUpdate.jobSalary > 70000) {
                return false;
            }
        }
        //check if senior or executive are part-timers
        if (jobUpdate.jobType.equals("Part time") && (jobUpdate.jobExperienceLevel.equals("Senior") || jobUpdate.jobExperienceLevel.equals("Executive"))) {
            return false;
        }
        //check if there is more than 3 skills
        if (jobUpdate.jobRequiredSkills.length > 3 ) {
            return false;
        }
        //check if job skill has two words
        for (int i = 0; i < jobUpdate.jobRequiredSkills.length; i++) {
            String[] check = jobUpdate.jobRequiredSkills[i].split(" ");
            if (check.length > 2) {
                return  false;
            }
        }


        double difference = jobUpdate.jobSalary - job.jobSalary;
        double average = (jobUpdate.jobSalary + job.jobSalary)/2;
        double percentage = (difference/average);
        System.out.println(percentage);
        //check senior and executive salary increase
        if (jobUpdate.jobExperienceLevel.equals("Senior") || jobUpdate.jobExperienceLevel.equals("Executive")) {
            if ((percentage <= 0.2 || percentage >= 0.4)) {
                System.out.println("Failed");
                return false;
            }
        }
        //check junior salary increase
        if (jobUpdate.jobExperienceLevel.equals("Junior")) {
            if ((percentage > 0.1)) {
                return false;
            }
        }
        //check job type change
        if (job.jobType.equals("Full time") && (jobUpdate.jobType.equals("Part time") || jobUpdate.jobType.equals("Volunteer"))) {
            return false;
        }


        return  true;
    }




}
