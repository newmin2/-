package state;

import context.LaptopContext;

public interface PowerState {
    void powerButtonPush(LaptopContext context);
    void typeButtonPush();
}
