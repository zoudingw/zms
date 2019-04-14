package com.zdw.zms.entity;

public class MyShiroRealm /*extends AuthorizingRealm*/ {

   /* @Autowired
    IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        *//*权限配置*//*
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = user.getRoleList();
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getRoleName());
            List<MyRight> rights = role.getRights();
            for (MyRight myRight : rights) {
                authorizationInfo.addStringPermission(myRight.getRightName());
            }
        }
        return authorizationInfo;
    }

    *//*从token中获取用户信息，然后从数据库中比对，进行身份认证*//*
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       String userName = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        User user = userService.getUserByUserName(userName);
    if(null == user)
        return null;
        SimpleAuthenticationInfo  authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(userName+user.getSalt()),getName());
        return authenticationInfo;
    }*/


}
