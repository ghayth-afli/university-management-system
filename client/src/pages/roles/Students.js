import React from 'react';
import { Space, Table, Tag, Modal, Button } from 'antd';
import {
  Checkbox,
  Divider,
  FormControlLabel,
  FormHelperText,
  Grid,
  Link,
  IconButton,
  InputAdornment,
  InputLabel,
  OutlinedInput,
  Stack,
  Typography
} from '@mui/material';
import { ExclamationCircleFilled } from '@ant-design/icons';
const { confirm } = Modal;

const Students = () => {
  const columns = [
    {
      title: 'Id',
      dataIndex: 'id',
      key: 'id'
    },
    {
      title: 'FisrtName',
      dataIndex: 'firstName',
      key: 'firstName',
      render: (text) => <a>{text}</a>
    },
    {
      title: 'LastName',
      dataIndex: 'lastName',
      key: 'lastName',
      render: (text) => <a>{text}</a>
    },
    {
      title: 'Phone Number',
      dataIndex: 'phoneNumber',
      key: 'phoneNumber'
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email'
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address'
    },
    {
      title: 'Age',
      dataIndex: 'age',
      key: 'age'
    },

    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size="middle">
          <a onClick={() => showPromiseUpdate(record)}>Update </a>
          <a onClick={() => showConfirm(record)}>Delete</a>
        </Space>
      )
    }
  ];
  const [data, setData] = React.useState([]);
  const [formData, setFormData] = React.useState({
    firstName: '',
    lastName: '',
    age: '',
    email: '',
    phoneNumber: '',
    address: ''
  });

  React.useEffect(() => {
    fetch('http://localhost:8000/students')
      .then((response) => response.json())
      .then((data) => setData(data))
      .catch((error) => console.error('Error:', error));
  }, []);

  const formDataRef = React.useRef();
  formDataRef.current = formData;
  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormData({
      ...formData,
      [name]: value
    });
  };

  const showPromiseUpdate = (student) => {
    console.log(student);
    confirm({
      title: 'Update Student',
      icon: <ExclamationCircleFilled />,
      content: (
        <>
          <form>
            <Grid container spacing={3}>
              <Grid item xs={10}>
                <Stack spacing={2}>
                  <InputLabel htmlFor="first-name">First Name</InputLabel>
                  <OutlinedInput
                    id="first-name"
                    type="text"
                    name="firstName"
                    value={student.firstName}
                    onChange={handleChange}
                    placeholder="Enter first name"
                    fullWidth
                  />
                  <InputLabel htmlFor="last-name">Last Name</InputLabel>
                  <OutlinedInput
                    id="last-name"
                    type="text"
                    name="lastName"
                    value={student.lastName}
                    onChange={handleChange}
                    placeholder="Enter last name"
                    fullWidth
                  />
                  <InputLabel htmlFor="age">Age</InputLabel>
                  <OutlinedInput
                    id="age"
                    type="text"
                    name="age"
                    value={student.age}
                    onChange={handleChange}
                    placeholder="Enter age"
                    fullWidth
                  />
                  <InputLabel htmlFor="email">Email Address</InputLabel>
                  <OutlinedInput
                    id="email"
                    type="email"
                    name="email"
                    value={student.email}
                    onChange={handleChange}
                    placeholder="Enter email address"
                    fullWidth
                  />
                  <InputLabel htmlFor="phone-number">Phone Number</InputLabel>
                  <OutlinedInput
                    id="phone-number"
                    type="text"
                    name="phoneNumber"
                    value={student.phoneNumber}
                    onChange={handleChange}
                    placeholder="Enter phone number"
                    fullWidth
                  />
                  <InputLabel htmlFor="address">Address</InputLabel>
                  <OutlinedInput
                    id="address"
                    type="text"
                    name="address"
                    value={student.address}
                    onChange={handleChange}
                    placeholder="Enter address"
                    fullWidth
                  />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        fetch(`http://localhost:8080/students/${student.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formDataRef.current)
        })
          .then((response) => response.json())
          .then((data) => console.log(data))
          .catch((error) => {
            console.error('Error:', error);
          });
      },
      onCancel() {}
    });
  };

  const showConfirm = (student) => {
    confirm({
      title: 'Do you Want to delete this item?',
      icon: <ExclamationCircleFilled />,
      content: '',
      onOk() {
        fetch(`http://localhost:8080/students/${student.id}`, {
          method: 'DELETE'
        })
          .then((response) => response.json())
          .then((data) => {
            console.log(data);
            success();
          })
          .catch((error) => {
            console.error('Error:', error);
          });
      },
      onCancel() {
        console.log('Cancel');
      }
    });
  };

  const success = () => {
    Modal.success({
      content: 'Student deleted successfully!'
    });
  };

  return (
    <>
      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Students;
