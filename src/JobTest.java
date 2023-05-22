import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void validAddJob1() {
        String [] array = {"Python"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 40000, "Description example" );
        assertTrue(job.addJob(job));
    }

    @Test
    void validAddJob2() {
        String [] array = {"Python,C++,SQL"};
            Job job = new Job("13132DEF$", "Job2", "Doe", "Sydney,New South Wales,Australia", "2023-02-01", "Senior", "Full time", array, 111000, "Description example" );
        assertTrue(job.addJob(job));
    }

    @Test
    void invalidAddJobIncorrectJobName1() {
        String [] array = {"Python"};
        Job job = new Job("242415ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 40000, "Description example" );
        assertFalse(job.addJob(job));
    }

    @Test
    void invalidAddJobIncorrectJobName2() {
        String [] array = {"Python"};
        Job job = new Job("13136###A", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 40000, "Description example" );
        assertFalse(job.addJob(job));
    }

    @Test
    void invalidAdJobIncorrectSalary1() {
        String [] array = {"Python"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 39999, "Description example" );
        assertFalse(job.addJob(job));
    }

    @Test
    void invalidAdJobIncorrectSalary2() {
        String [] array = {"Python,C++,SQL"};
        Job job = new Job("13132DEF$", "Job2", "Doe", "Sydney,New South Wales,Australia", "2023-02-01", "Junior", "Full time", array, 70001, "Description example" );
        assertFalse(job.addJob(job));
    }

    @Test
    void validUpdateJob1() throws IOException, ClassNotFoundException {
        String [] array = {"Java"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 40000, "Description example" );
        assertTrue(job.updateJob(job));
    }

    @Test
    void validUpdateJob2() throws IOException, ClassNotFoundException {
        String [] array = {"Python"};
        Job job = new Job("13132DEF$", "Job2", "Doe", "Sydney,New South Wales,Australia", "2023-02-01", "Junior", "Full time", array, 40000, "Description example" );
        assertTrue(job.updateJob(job));
    }

    @Test
    void invalidUpdateJobIncorrectSalaryIncrease1() throws IOException, ClassNotFoundException {
        String [] array = {"Python"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Full time", array, 49000, "Description example" );
        assertFalse(job.updateJob(job));
    }

    @Test
    void invalidUpdateJobIncorrectSalaryIncrease2() throws IOException, ClassNotFoundException {
        String [] array = {"Python"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Senior", "Full time", array, 47600, "Description example" );
        assertFalse(job.updateJob(job));
    }

    @Test
    void invalidUpdateJobIncorrectExperienceLevelChange1() throws IOException, ClassNotFoundException {
        String [] array = {"Python"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Part time", array, 40000, "Description example" );
        assertFalse(job.updateJob(job));
    }

    @Test
    void invalidUpdateJobIncorrectExperienceLevelChange2() throws IOException, ClassNotFoundException {
        String [] array = {"Python"};
        Job job = new Job("24241ABC#", "Job1", "John", "Sydney,New South Wales,Australia", "2023-01-01", "Junior", "Volunteer", array, 40000, "Description example" );
        assertFalse(job.updateJob(job));
    }
}