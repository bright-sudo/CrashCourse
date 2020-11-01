import React, {Component} from 'react';
import {getAllStudentCourse} from '../client';
import {Table, Empty} from 'antd';
import Conteiner from '../Conteiner';
import {infoNotification} from '../notification';
import { Spin } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';


const antIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

class Courses extends Component {

   state = {
      courses: [],
      studentId: '',
      isFetching: false
   }
   componentDidMount () {
      this.fetchStudentCourses();
    }

   fetchStudentCourses = () => {
      this.setState({isFetching: true})
      const studentId = this.props.match.params.studentId;
      this.setState({studentId});
      getAllStudentCourse(studentId)
         .then(res => res.json())
         .then(course => {
            this.setState({courses: course})
            this.setState({isFetching: false})
         }).catch(err => {
            const error = err.error.error;
            const description = err.error.description;
            infoNotification(error, description);
            this.setState({isFetching: false})
            
         })
   } 
    render() {
      const {courses, isFetching} = this.state;
      if(isFetching) {
         return (
            <div className='spinner'>
               <Spin indicator={antIcon()} />
            </div>
         )
      }
      if(courses && courses.length) {

         const columns = [
            {
               title: 'Course Id',
               dataIndex: 'courseId',
               key: 'courseId'
            },
            {
               title: 'Department',
               dataIndex: 'department',
               key: 'department'
            },
            {
               title: 'Description',
               dataIndex: 'description',
               key: 'description'
            },
            {
               title: 'Grade',
               dataIndex: 'grade',
               key: 'grade'
            },
            {
               title: 'Name',
               dataIndex: 'name',
               key: 'name'
            },
            {
               title: 'Start Date',
               dataIndex: 'startDate',
               key: 'startDate'
            },
            {
               title: 'End Date',
               dataIndex: 'endDate',
               key: 'endDate'
            },
            {
               title: 'Teacher Name',
               dataIndex: 'teacherName',
               key: 'teacherName'
            }
         ]
        
         return (
            <Conteiner>
               <Table 
                  dataSource={courses} 
                  columns={columns}
                  rowKey='courseId'
                  pagination= {false}
                  />
            </Conteiner>
         )
      } else {
         return (<Conteiner>
            <Empty 
               style={{marginTop:'9em' }}
               image={Empty.PRESENTED_IMAGE_SIMPLE}
               description={
                  'No course found'
               }
               />
         </Conteiner>
         );
      }
      
      
    }
}
 
export default Courses;