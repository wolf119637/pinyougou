package dubbodemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import dubbodemo.service.UserService;

/**
 * Created by kim on 2019/5/22.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getName() {
        return "itcast";
    }
}
