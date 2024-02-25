import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      links={[
        {
          key: 'Ant Design Pro',
          title: 'Ant Design Pro',
          href: 'https://pro.ant.design',
          blankTarget: true,
        },{
          key: 'LinkedIn',
          title: 'LinkedIn',
          href: 'https://www.linkedin.com/in/jijiezhou/',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <><GithubOutlined />Jijie Zhou</>,
          href: 'https://github.com/jijiezhou',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
