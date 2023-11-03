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

const Teachers = () => {
  const token = localStorage.getItem('token');
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
      title: 'Salary',
      dataIndex: 'salary',
      key: 'salary'
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
    email: '',
    phoneNumber: '',
    address: '',
    salary: ''
  });

  React.useEffect(() => {
    fetch('http://localhost:8081/api/teachers', {
      method: 'GET',
      headers: new Headers({
        Authorization: `Bearer ${token}`
      })
    })
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

  const showPromiseUpdate = (teacher) => {
    console.log(teacher);
    confirm({
      title: 'Update teacher',
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
                    defaultValue={teacher.firstName}
                    onChange={handleChange}
                    placeholder="Enter first name"
                    fullWidth
                  />
                  <InputLabel htmlFor="last-name">Last Name</InputLabel>
                  <OutlinedInput
                    id="last-name"
                    type="text"
                    name="lastName"
                    defaultValue={teacher.lastName}
                    onChange={handleChange}
                    placeholder="Enter last name"
                    fullWidth
                  />
                  <InputLabel htmlFor="email">Email Address</InputLabel>
                  <OutlinedInput
                    id="email"
                    type="email"
                    name="email"
                    defaultValue={teacher.email}
                    onChange={handleChange}
                    placeholder="Enter email address"
                    fullWidth
                  />
                  <InputLabel htmlFor="phone-number">Phone Number</InputLabel>
                  <OutlinedInput
                    id="phone-number"
                    type="text"
                    name="phoneNumber"
                    defaultValue={teacher.phoneNumber}
                    onChange={handleChange}
                    placeholder="Enter phone number"
                    fullWidth
                  />
                  <InputLabel htmlFor="address">Address</InputLabel>
                  <OutlinedInput
                    id="address"
                    type="text"
                    name="address"
                    defaultValue={teacher.address}
                    onChange={handleChange}
                    placeholder="Enter address"
                    fullWidth
                  />
                  <InputLabel htmlFor="salary">Salary</InputLabel>
                  <OutlinedInput
                    id="salary"
                    type="text"
                    name="salary"
                    defaultValue={teacher.salary}
                    onChange={handleChange}
                    placeholder="Enter salary"
                    fullWidth
                  />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        fetch(`http://localhost:8081/api/teachers/${teacher.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
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

  const showConfirm = (teacher) => {
    confirm({
      title: 'Do you Want to delete this item?',
      icon: <ExclamationCircleFilled />,
      content: '',
      onOk() {
        fetch(`http://localhost:8081/api/teachers/${teacher.id}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          }
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
  const showAddTeacherForm = () => {
    confirm({
      title: 'Add new teacher',
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
                    onChange={handleChange}
                    placeholder="Enter first name"
                    fullWidth
                  />
                  <InputLabel htmlFor="last-name">Last Name</InputLabel>
                  <OutlinedInput
                    id="last-name"
                    type="text"
                    name="lastName"
                    onChange={handleChange}
                    placeholder="Enter last name"
                    fullWidth
                  />
                  <InputLabel htmlFor="email">Email Address</InputLabel>
                  <OutlinedInput id="email" type="email" name="email" onChange={handleChange} placeholder="Enter email address" fullWidth />
                  <InputLabel htmlFor="phone-number">Phone Number</InputLabel>
                  <OutlinedInput
                    id="phone-number"
                    type="text"
                    name="phoneNumber"
                    onChange={handleChange}
                    placeholder="Enter phone number"
                    fullWidth
                  />
                  <InputLabel htmlFor="address">Address</InputLabel>
                  <OutlinedInput id="address" type="text" name="address" onChange={handleChange} placeholder="Enter address" fullWidth />
                  <InputLabel htmlFor="salary">Salary</InputLabel>
                  <OutlinedInput id="salary" type="text" name="salary" onChange={handleChange} placeholder="Enter salary" fullWidth />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        fetch('http://localhost:8081/api/teachers', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
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
  const success = () => {
    Modal.success({
      content: 'Teacher deleted successfully!'
    });
  };

  return (
    <>
      <Button type="primary" onClick={showAddTeacherForm}>
        Add Teacher
      </Button>

      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Teachers;
