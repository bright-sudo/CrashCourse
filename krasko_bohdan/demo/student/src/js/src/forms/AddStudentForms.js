import React from 'react';
import { Formik } from 'formik';
import {Input, Button, Tag } from 'antd';
import { addNewStudent } from '../client'

const marginBottom = {marginBottom: '10px'};

const AddStudentForm = (props) => 
  (
    <Formik
      initialValues={{firstName: '', lastName: '', email: '', gender: '' }}
      validate={values => {
        const errors = {};

        if(!values.firstName) {
          errors.firstName = 'First Name required';
        }
        if(!values.lastName) {
          errors.lastName = 'Last Name required';
        }
        if (!values.email) {
          errors.email = 'Email required';
        } else if (
          !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
        ) {
          errors.email = 'Invalid email address';
        }
        if(!values.gender) {
          errors.gender = 'Gender required';
        } else if (!['MALE', 'male', 'FEMALE', 'female'].includes(values.gender)) {
          errors.gender = 'Gender must be (MALE, male, FEMALE)';
        }

        return errors;
      }}
      onSubmit={(values, { setSubmitting }) => {
          addNewStudent(values).then(() => {
            props.onSuccess();
          }).catch(error => {
            props.onFailuer(error);
          }).finally(setSubmitting(false))
          
      }}
    >
      {({
        values,
        errors,
        touched,
        handleChange,
        handleBlur,
        handleSubmit,
        isSubmitting,
        submitForm,
        isValid
        /* and other goodies */
      }) => (
        <form onSubmit={handleSubmit}>
          <Input
            style={marginBottom}
            name="firstName"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.firstName}
            placeholder='First name. E.g. John'
          />
          {errors.firstName && touched.firstName && <Tag color='red' style={marginBottom}>{errors.firstName}</Tag>}
          <Input
            style={marginBottom}
            name="lastName"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.lastName}
            placeholder='Last name. E.g. Nelson'
          />
          {errors.lastName && touched.lastName && <Tag color='red' style={marginBottom}>{errors.lastName}</Tag>}
          <Input
            style={marginBottom}
            name="email"
            type='email'
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.email}
            placeholder='Email. E.g. example@gmail.com'
          />
          {errors.email && touched.email && <Tag color='red' style={marginBottom}>{errors.email}</Tag>}
          <Input
            style={marginBottom}
            name="gender"                  
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.gender}
            placeholder='Gender. E.g. MALE or FEMALE'
          />
          {errors.gender && touched.gender && <Tag color='red' style={marginBottom}>{errors.gender}</Tag>}
          
          <Button 
            onClick={() => submitForm()} 
            type="submit" 
            disabled={isSubmitting || (touched && !isValid)}>
            Submit
          </Button>
        </form>
      )}
    </Formik>
        
        );
export default AddStudentForm;