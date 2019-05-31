package com.pinyougou.shop.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * Created by kim
 * on 2019/5/26.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    public static String SELLER_AUTHOR_STATUE = "1";

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    private SellerService sellerService;




    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //构建角色列表
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));

        System.out.println("经过验证");
        //得倒商家对象
        TbSeller seller = sellerService.findOne(username);

        if (seller != null) {

            if (SELLER_AUTHOR_STATUE.equals(seller.getStatus())){
               return new User(username,seller.getPassword(),grantedAuthorities);
            }else {
                return null;
            }
        }else {
            return null;
        }



    }




}
