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

const Administration = () => {
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
      title: 'Position',
      dataIndex: 'position',
      key: 'position'
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
    position: '',
    email: '',
    phoneNumber: '',
    address: ''
  });

  React.useEffect(() => {
    fetch('http://localhost:8081/api/administrators', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      }
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setData(data);
      })
      .catch((error) => console.error('Error:', error));
  }, []);

  const formDataRef = React.useRef();
  formDataRef.current = formData;
  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value
    }));
  };

  const showPromiseUpdate = (admin) => {
    console.log(admin);
    confirm({
      title: 'Update admin',
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
                    defaultValue={admin.firstName}
                    onChange={handleChange}
                    placeholder="Enter first name"
                    fullWidth
                  />
                  <InputLabel htmlFor="last-name">Last Name</InputLabel>
                  <OutlinedInput
                    id="last-name"
                    type="text"
                    name="lastName"
                    defaultValue={admin.lastName}
                    onChange={handleChange}
                    placeholder="Enter last name"
                    fullWidth
                  />
                  <InputLabel htmlFor="email">Email Address</InputLabel>
                  <OutlinedInput
                    id="email"
                    type="email"
                    name="email"
                    defaultValue={admin.email}
                    onChange={handleChange}
                    placeholder="Enter email address"
                    fullWidth
                  />
                  <InputLabel htmlFor="phone-number">Phone Number</InputLabel>
                  <OutlinedInput
                    id="phone-number"
                    type="text"
                    name="phoneNumber"
                    defaultValue={admin.phoneNumber}
                    onChange={handleChange}
                    placeholder="Enter phone number"
                    fullWidth
                  />
                  <InputLabel htmlFor="address">Address</InputLabel>
                  <OutlinedInput
                    id="address"
                    type="text"
                    name="address"
                    defaultValue={admin.address}
                    onChange={handleChange}
                    placeholder="Enter address"
                    fullWidth
                  />
                  <InputLabel htmlFor="position">Position</InputLabel>
                  <OutlinedInput
                    id="position"
                    type="text"
                    name="position"
                    defaultValue={admin.position}
                    onChange={handleChange}
                    placeholder="Enter position"
                    fullWidth
                  />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        const combinedData = Object.keys(formDataRef.current).reduce((result, key) => {
          if (formDataRef.current[key] || admin[key]) {
            result[key] = formDataRef.current[key] || admin[key];
          }
          return result;
        }, {});
        fetch(`http://localhost:8081/api/administrators/${admin.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
          },
          body: JSON.stringify(combinedData)
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

  const showConfirm = (admin) => {
    confirm({
      title: 'Do you Want to delete this item?',
      icon: <ExclamationCircleFilled />,
      content: '',
      onOk() {
        fetch(`http://localhost:8081/api/administrators/${admin.id}`, {
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
            setData((prevData) => prevData.filter((item) => item.id !== admin.id));
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
  const showAddAdminForm = () => {
    confirm({
      title: 'Add new admin',
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
                  <InputLabel htmlFor="position">Position</InputLabel>
                  <OutlinedInput id="position" type="text" name="position" onChange={handleChange} placeholder="Enter position" fullWidth />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        fetch('http://localhost:8081/api/administrators', {
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
      content: 'admin deleted successfully!'
    });
  };

  return (
    <>
      <Button onClick={showAddAdminForm} type="primary" style={{ marginBottom: 16 }}>
        Add Administration
      </Button>
      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Administration;
