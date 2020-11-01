import fetch from 'unfetch';

const checkStatus = response => {
    if(response.ok) {
        return response;
    } else {
        let error = new Error(response.statusText);
        error.response = response;
        response.json().then(e => {
            error.error = e;
        });
        return Promise.reject(error);
    }
}

export const getAllStudents = ()  => 
    fetch('/api/students').then(checkStatus);

export const getOffsetStudents = (offset, limit) => 
    fetch(`/api/students/${offset}/${limit}`, {
        method: 'GET'
    })
    .then(checkStatus);

export const getAllStudentCourse = (studentId) => 
    fetch(`/api/students/${studentId}/course`)
    .then(checkStatus);

export const addNewStudent = student => 
fetch('/api/students', {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify(student)
}).then(checkStatus);

export const deleteStudent = studentId => 
    fetch(`/api/students/${studentId}`, {
        method: 'DELETE'
    }).then(checkStatus);

export const updateStudent = (studnetId, student) => 
    fetch(`/api/students/${studnetId}`, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(student)
    }).then(checkStatus);

    export const getCountStudents = () => 
        fetch('/api/students/count').then(checkStatus);