# Study log

## ~ Task 2 ~ Proposal
<T>I will implement a **study log** application. This application will 
allow *learners* to save their study as data.
I have used the term *learners* because anyone who is a *learner*
can use it.
<T>The objective of this application is to motivate
*learners* to learn more by visualizing how much they have studied 
over a certain period. Students who are struggling to maintain their
study habit could use this application as one of the incentives of studying. 
Another user example might be a
person who is interested in learning how to play the piano. Transforming the learner's
effort directly into a visual representation will boost the willingness to
learn. 


<T>This project is of interest to me because I am currently using a
study log application. I believe my study habit has drastically 
improved thanks to this application. Thus, I want to share 
this benefit by simplifying the current study log and adding new functionality. 
Another reason to choose this as my project is to figure out how to 
spark the studying motivation inside a student. Over my time as a student 
until now, I have witnessed countless students who do not enjoy studying at all. 
Their studying is **dead studying** because they do not fully appreciate the 
power of studying. We owe what we are today to studying. 
We are studying every day. In fact, we are studying every second. 
We are studying how to write when texting a friend. We are
studying video games when playing video games. We are studying 
how to breathe when we breathe. We have to study to improve at anything.
In our short life, we must ask ourselves. What do I want to study? 
I wish I can change as many student as possible so that they start the 
habit of **lively studying** through this study log.

## ~ Task 3 ~ User stories:

### Completed Implementation:
- As a user, I want to be able to add multiple studied materials into my study log
- As a user, I want to be able to view all my studied materials in my study log as a timeline
- As a user, I want to be able to add new subjects into my study subject list
- As a user, I want to be able to save the study time, the exact time I started studying, the exact time I ended
studying, the study subject and the specific study content into my study log as studied materials
- As a user, I want to be able to have the option to save my study log to file
- As a user, I want to be able to have the option to reload my study log from file

### Implementation Ideas:
- As a user, I want to be able to edit or delete the studied material in the study log
- As a user, I want to be able to have a stop watch feature while I study
- As a user, I want to be able to see how much I studied today, this week, this month and in total
- As a user, I want to be able to see the average study time per day for today, this week, this month and in total
- As a user, I want to be able to see how much I studied for each subject
- As a user, I want to be able to set a study time goal for one day, one week, one month and in total
- As a user, I want to be able to save which day I studied in the calendar
- As a user, I want to be able to see the study streak (how many consecutive days I was able to study)
- As a user, I want to be able to set a study streak goal 
- As a user, I want to be able to know my study rating that depends on the study streak and study time 
- As a user, I want to be able to see my study rating visually
- As a user, I want to be able to have achievement medals for outstanding accomplishments (e.g. 100 hours study time or 30 days
  study streak)

## ~ Phase 3 ~ Instructions for Grader:
- You can generate the first required action related to 
the user story "adding multiple Xs to a Y" by first putting
the name of the study subject in the editable text box at
the middle. Then, please click the "Add Subject" button. 
After that, please click the "Start/End Studying" button 
and please follow the instructions shown in the uneditable 
text box to add a study material in the StudyLog.
- You can generate the second required action related to 
the user story "adding multiple Xs to a Y" by putting a name
of an added subject in the editable text box and clicking 
the "Filter Study Log" button.
- You can locate my visual component by clicking the
  "Start/End Studying" button multiple times in a row.
- You can save the state of my application by clicking the
  "Save" button.
- You can reload the state of my application by clicking 
the "Load" button.

### Citation: 
https://www.youtube.com/watch?v=Kmgo00avvEw
https://www.ac-illust.com/main/search_result.php?word=goodjob

## ~ Phase 4: Task 2 ~
- Set Study Time for the StudiedMaterial.
- Set Study Start Time for the StudiedMaterial.
- Set Study End Time for the StudiedMaterial.
- Set Study Content for the StudiedMaterial.
- Set Study Subject for the StudiedMaterial.
- Set Subject for the StudySubject.
- Added Subject to the SubjectList.
- Added Study Task to the StudyLog.
- Searched for the Subject in the SubjectList.

## ~ Phase 4: Task 3 ~
There are three changes I would like to make if I had more time.
Firstly, I would like to delete the association from StudyLogDisplay to StudySubject.
As we can see from the associations of StudyLogGUI, there is no association to StudySubject.
During the implementation of StudyLogGUI, I have realized that the class did not need an association and therefore I 
also realized that StudyLogDisplay could be refactored as well. Generally, redundant association increases coupling
unnecessarily, so the association should be refactored.
Next, I would like to delete the association from StudySubject to itself. 
This was a design mistake also made when implementing StudyLogDisplay.
Since StudyLog already has a direct association to StudySubject and the StudySubjectList does not change 
depending on the StudiedMaterial, it is better to delete the association from StudySubject to itself to loosen 
coupling.
Lastly, I would like to refactor StudyLogGUI because I believe it violates the Single Responsibility Principle.
This is evident from the number of fields it has which is twenty-one. 
Buttons and text fields could be in a new class to achieve higher cohesion. 


