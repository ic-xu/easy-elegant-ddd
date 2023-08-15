/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensource.easyddd.infrastructure.config.apilog.log;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现Spring Security的UserDetails接口
 *
 * @author terrfly
 * @site <a href="https://www.jeequan.com">...</a>
 * @date 2021/6/8 16:34
 */
@Slf4j
@Data
public class JeeUserDetails {


    /**
     * 密码
     **/
    private String credential;


    /**
     * 缓存标志
     **/
    private String cacheKey;

    /**
     * 登录IP
     **/
    private String loginIp;

    //此处的无参构造，为json反序列化提供

    public JeeUserDetails(String credential) {

        this.setCredential(credential);

        //做一些初始化操作
    }


    public String getPassword() {
        return getCredential();
    }




    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }



}
