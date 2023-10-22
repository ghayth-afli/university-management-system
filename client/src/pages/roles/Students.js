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

const success = () => {
  Modal.success({
    content: 'Student deleted successfully!'
  });
};

const Students = () => {
  const [formData, setFormData] = React.useState({
    firstName: '',
    lastName: '',
    age: '',
    email: '',
    phoneNumber: '',
    address: ''
  });

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormData({
      ...formData,
      [name]: value
    });
    console.log(formData);
  };

  const showPromiseUpdate = () => {
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
                    value={formData.firstName}
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
                    value={formData.lastName}
                    onChange={handleChange}
                    placeholder="Enter last name"
                    fullWidth
                  />
                  <InputLabel htmlFor="age">Age</InputLabel>
                  <OutlinedInput
                    id="age"
                    type="text"
                    name="age"
                    value={formData.age}
                    onChange={handleChange}
                    placeholder="Enter age"
                    fullWidth
                  />
                  <InputLabel htmlFor="email">Email Address</InputLabel>
                  <OutlinedInput
                    id="email"
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="Enter email address"
                    fullWidth
                  />
                  <InputLabel htmlFor="phone-number">Phone Number</InputLabel>
                  <OutlinedInput
                    id="phone-number"
                    type="text"
                    name="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={handleChange}
                    placeholder="Enter phone number"
                    fullWidth
                  />
                  <InputLabel htmlFor="address">Address</InputLabel>
                  <OutlinedInput
                    id="address"
                    type="text"
                    name="address"
                    value={formData.address}
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
        return new Promise((resolve, reject) => {
          setTimeout(Math.random() > 0.5 ? resolve : reject, 1000);
        }).catch(() => console.log('Oops errors!'));
      },
      onCancel() {}
    });
  };

  const showConfirm = () => {
    confirm({
      title: 'Do you Want to delete this item?',
      icon: <ExclamationCircleFilled />,
      content: '',
      onOk() {
        success();
        console.log('OK');
      },
      onCancel() {
        console.log('Cancel');
      }
    });
  };
  const columns = [
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
      render: (text) => <a>{text}</a>
    },
    {
      title: 'Birth Date',
      dataIndex: 'birthDate',
      key: 'birthDate'
    },
    {
      title: 'Grade',
      dataIndex: 'grade',
      key: 'grade'
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address'
    },
    {
      title: 'Tags',
      key: 'tags',
      dataIndex: 'tags',
      render: (_, { tags }) => (
        <>
          {tags.map((tag) => {
            let color = tag.length > 5 ? 'geekblue' : 'green';
            if (tag === 'loser') {
              color = 'volcano';
            }
            return (
              <Tag color={color} key={tag}>
                {tag.toUpperCase()}
              </Tag>
            );
          })}
        </>
      )
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, record) => (
        <Space size="middle">
          <a>View {record.name}</a>
          <a onClick={showPromiseUpdate}>Update </a>

          <a onClick={showConfirm}>Delete</a>
        </Space>
      )
    }
  ];
  const data = [
    {
      key: '1',
      name: 'John Brown',
      birthDate: '1998-02-02',
      grade: '3',
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer']
    },
    {
      key: '2',
      name: 'Jim Green',
      birthDate: '1998-15-02',
      grade: '3',
      address: 'London No. 1 Lake Park',
      tags: ['loser']
    },
    {
      key: '3',
      name: 'Joe Black',
      birthDate: '1998-02-02',
      grade: '4',
      address: 'Sydney No. 1 Lake Park',
      tags: ['cool', 'teacher']
    }
  ];

  return (
    <>
      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Students;
