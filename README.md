# StudentInformationSystem-CSYE6225

ELB URL
http://studentinformationsystemxiao-env.zsaqrntfjf.us-east-2.elasticbeanstalk.com

##API
Professor
GET   /professors
GET   /professors/{professorID}
POST  /professors    Example Body:{"professorName": "John", "professorID": "11111", "department": "engineering", "contactedEmail": "x@husky.neu.edu", "teachedCourse": ["cloud"]}
PUT   /professors/{professorID}
DELETE   /professors/{professorID}

Student
GET   /students
GET   /students/{studentID}
POST  /students      Example Body:{"studentName": "Amy", "studentID": "12345", "imageURL": "xxxxx", "enrolledCourses": null, "programName": "computer"}
PUT   /students/{studentID}
DELETE   /students/{studentID}


Program
GET   /programs
GET   /programs/{programName}
POST  /programs     Example Body:{"programName": "Information Systems", "programCourses": null}
PUT   /programs/{programName}
DELETE   /programs/{programName}

