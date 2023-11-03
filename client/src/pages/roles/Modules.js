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
  Typography,
  Select,
  MenuItem
} from '@mui/material';
import { ExclamationCircleFilled } from '@ant-design/icons';
const { confirm } = Modal;

const Modules = () => {
  const columns = [
    {
      title: 'Id',
      dataIndex: 'id',
      key: 'id'
    },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
      render: (text) => <a>{text}</a>
    },
    {
      title: 'NbHours',
      dataIndex: 'nbHours',
      key: 'nbHours'
    },
    {
      title: 'Coefficient',
      dataIndex: 'coefficient',
      key: 'coefficient'
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
  const [data, setData] = React.useState([
    {
      id: 1,
      name: 'Math',
      nbHours: '10',
      coefficient: '2'
    },
    {
      id: 2,
      name: 'Physique',
      nbHours: '10',
      coefficient: '2'
    },
    {
      id: 3,
      name: 'Chimie',
      nbHours: '10',
      coefficient: '2'
    },
    {
      id: 4,
      name: 'Informatique',
      nbHours: '10',
      coefficient: '2'
    },
    {
      id: 5,
      name: 'Anglais',
      nbHours: '10',
      coefficient: '2'
    }
  ]);
  const [formData, setFormData] = React.useState({
    name: '',
    nbHours: '',
    coefficient: ''
  });

  React.useEffect(() => {
    fetch('http://localhost:8081/api/modules')
      .then((response) => response.json())
      .then((data) => setData(data))
      .catch((error) => console.error('Error:', error));
  }, []);

  const formDataRef = React.useRef();
  formDataRef.current = formData;

  const handleChange = (event) => {
    const { name, value } = event.target;
    console.log(name, value);

    setFormData({
      ...formData,
      [name]: value
    });
  };

  const showPromiseUpdate = (module) => {
    console.log(module);
    confirm({
      title: 'Update Classes',
      icon: <ExclamationCircleFilled />,
      content: (
        <>
          <form>
            <Grid container spacing={3}>
              <Grid item xs={10}>
                <Stack spacing={2}>
                  <InputLabel htmlFor="name">Name</InputLabel>
                  <OutlinedInput
                    id="name"
                    type="text"
                    name="name"
                    defaultValue={module.name}
                    onChange={handleChange}
                    placeholder="Enter name"
                    fullWidth
                  />
                  <InputLabel htmlFor="nbHours">NbHours</InputLabel>
                  <OutlinedInput
                    id="nbHours"
                    type="text"
                    name="nbHours"
                    defaultValue={module.nbHours}
                    onChange={handleChange}
                    placeholder="Enter number of hours"
                    fullWidth
                  />
                  <InputLabel htmlFor="coefficient">Coefficient</InputLabel>
                  <OutlinedInput
                    id="coefficient"
                    type="text"
                    name="coefficient"
                    defaultValue={module.coefficient}
                    onChange={handleChange}
                    placeholder="Enter coefficient"
                    fullWidth
                  />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        console.log(formData);
        fetch(`http://localhost:8081/api/modules/${module.id}`, {
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

  const showConfirm = (module) => {
    confirm({
      title: 'Do you Want to delete this item?',
      icon: <ExclamationCircleFilled />,
      content: '',
      onOk() {
        fetch(`http://localhost:8081/api/modules/${module.id}`, {
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
  const showAddModuleForm = () => {
    confirm({
      title: 'Add new teacher',
      icon: <ExclamationCircleFilled />,
      content: (
        <>
          <form>
            <Grid container spacing={3}>
              <Grid item xs={10}>
                <Stack spacing={2}>
                  <InputLabel htmlFor="name">Name</InputLabel>
                  <OutlinedInput id="name" type="text" name="name" onChange={handleChange} placeholder="Enter name" fullWidth />
                  <InputLabel htmlFor="nbHours">NbHours</InputLabel>
                  <OutlinedInput
                    id="nbHours"
                    type="text"
                    name="nbHours"
                    onChange={handleChange}
                    placeholder="Enter number of hours"
                    fullWidth
                  />
                  <InputLabel htmlFor="coefficient">Coefficient</InputLabel>
                  <OutlinedInput
                    id="coefficient"
                    type="text"
                    name="coefficient"
                    onChange={handleChange}
                    placeholder="Enter coefficient"
                    fullWidth
                  />
                </Stack>
              </Grid>
            </Grid>
          </form>
        </>
      ),
      onOk() {
        fetch('http://localhost:8081/api/modules', {
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
      content: 'Module deleted successfully!'
    });
  };

  return (
    <>
      <Button type="primary" onClick={showAddModuleForm}>
        Add Module
      </Button>

      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Modules;
