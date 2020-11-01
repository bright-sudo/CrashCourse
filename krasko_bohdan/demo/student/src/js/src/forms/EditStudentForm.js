import React from 'react';
import { Formik } from 'formik';
import { Button } from 'antd';
import FieldInput from '../component/FieldInput'
const  EditStudentForm = props => {
    
        const {submitter, initialValues } = props;
        
        return(
            
            <Formik
                initialValues = {initialValues}
                enableReinitialize={true}

                onSubmit = {(values, {setSubmitting}) => {
                    submitter(values);
                    setSubmitting(false);
                }}
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
                    } else if (!['MALE', 'FEMALE'].includes(values.gender)) {
                      errors.gender = 'Gender must be (MALE, FEMALE)';
                    }
            
                    return errors;
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
                    isValid,
                    
                }) => (
                    <form onSubmit={handleSubmit}>

                    <FieldInput 
                        name='firstName'
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.firstName}
                        placeholder='First name. E.g. John'
                        errors={errors.firstName}
                        touched={touched.firstName}
                    ></FieldInput>

                    <FieldInput 
                        name='lastName'
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.lastName}
                        placeholder='Last name. E.g. Way'
                        errors={errors.lastName}
                        touched={touched.lastName}
                    ></FieldInput>

                    <FieldInput 
                        type='email'
                        name='email'
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.email}
                        placeholder='Email. E.g. example@gmail.com'
                        errors={errors.email}
                        touched={touched.email}
                    ></FieldInput>
                    
                    <FieldInput 
                        name='gender'
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.gender}
                        placeholder='Gender. E.g. MALE or FEMALE'
                        errors={errors.gender}
                        touched={touched.gender}
                    ></FieldInput>
                   
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
}   

export default EditStudentForm;