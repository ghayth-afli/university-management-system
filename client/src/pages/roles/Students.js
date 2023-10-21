import React from 'react';
import { Space, Table, Tag, Modal, Button } from 'antd';
import { ExclamationCircleFilled } from '@ant-design/icons';
const { confirm } = Modal;

const success = () => {
  Modal.success({
    content: 'Student deleted successfully!'
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
        <a>Update </a>

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

const Students = () => {
  return (
    <>
      <Table columns={columns} dataSource={data} />
    </>
  );
};
export default Students;
