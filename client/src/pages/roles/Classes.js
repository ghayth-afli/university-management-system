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

const Classes = () => {
  const columns = [
    {
      title: 'Id',
      dataIndex: 'id',
      key: 'id'
    },
    {
      title: 'Speciality',
      dataIndex: 'speciality',
      key: 'speciality',
      render: (text) => <a>{text}</a>
    },
    {
      title: 'Grade',
      dataIndex: 'grade',
      key: 'grade'
    },
    {
      title: 'Group',
      dataIndex: 'grp',
      key: 'grp'
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
      speciality: 'TIC',
      grade: '1',
      grp: '1'
    },
    {
      id: 2,
      speciality: 'GLSI',
      grade: '1',
      grp: '2'
    }
  ]);
  const [formData, setFormData] = React.useState({
    speciality: '',
    grade: '',
    grp: ''
  });
  const [selectedRecord, setSelectedRecord] = React.useState(null);

  React.useEffect(() => {
    fetch('http://localhost:8000/classes')
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

  const showPromiseUpdate = (classe) => {
    console.log(classe);
    setSelectedRecord(classe);
    confirm({
      title: 'Update Classes',
      icon: <ExclamationCircleFilled />,
      content: (
        <>
          <form>
            <Grid container spacing={3}>
              <Grid item xs={10}>
                <Stack spacing={2}>
                  <InputLabel htmlFor="speciality">Speciality</InputLabel>
                  <Select id="speciality" label="Speciality" defaultValue={classe.speciality} onChange={handleChange}>
                    <MenuItem value={'TIC'}>TIC</MenuItem>
                    <MenuItem value={'GLSI'}>GLSI</MenuItem>
                    <MenuItem value={'SSIR'}>SSIR</MenuItem>
                    <MenuItem value={'DSEN'}>DSEN</MenuItem>
                    <MenuItem value={'DMWM'}>DMWM</MenuItem>
                  </Select>
                  <InputLabel htmlFor="grade">Grade</InputLabel>
                  <OutlinedInput
                    id="grade"
                    type="text"
                    name="grade"
                    defaultValue={classe.grade}
                    onChange={handleChange}
                    placeholder="Enter grade"
                    fullWidth
                  />
                  <InputLabel htmlFor="grp">Group</InputLabel>
                  <OutlinedInput
                    id="grp"
                    type="text"
                    name="grp"
                    defaultValue={classe.grp}
                    onChange={handleChange}
                    placeholder="Enter group"
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
        fetch(`http://localhost:8080/classes/${classe.id}`, {
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

  const showConfirm = (classe) => {
    confirm({
      title: 'Do you Want to delete this item?',
      icon: <ExclamationCircleFilled />,
      content: '',
      onOk() {
        fetch(`http://localhost:8080/classes/${classe.id}`, {
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
      content: 'Classe deleted successfully!'
    });
  };

  return (
    <>
      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Classes;
