These are examples from "Embedded Linux Systems with the Yocto Project".

## Initializing the project

Within the this directory, clone poky.
```
git clone git://git.yoctoproject.org/poky
```
To initialize the tool chain, run 
```
 poky/oe-init-build-env
 ```

 This will set up `bitbake` for use.

## Building Projects

### Building nano

Run 
```
bitbake nano
```

