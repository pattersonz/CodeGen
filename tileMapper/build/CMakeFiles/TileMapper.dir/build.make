# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.14

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /terraForce/coding/snes/CodeGen/tileMapper

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /terraForce/coding/snes/CodeGen/tileMapper/build

# Include any dependencies generated for this target.
include CMakeFiles/TileMapper.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/TileMapper.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/TileMapper.dir/flags.make

CMakeFiles/TileMapper.dir/TileMapper.cpp.o: CMakeFiles/TileMapper.dir/flags.make
CMakeFiles/TileMapper.dir/TileMapper.cpp.o: ../TileMapper.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/terraForce/coding/snes/CodeGen/tileMapper/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/TileMapper.dir/TileMapper.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/TileMapper.dir/TileMapper.cpp.o -c /terraForce/coding/snes/CodeGen/tileMapper/TileMapper.cpp

CMakeFiles/TileMapper.dir/TileMapper.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/TileMapper.dir/TileMapper.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /terraForce/coding/snes/CodeGen/tileMapper/TileMapper.cpp > CMakeFiles/TileMapper.dir/TileMapper.cpp.i

CMakeFiles/TileMapper.dir/TileMapper.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/TileMapper.dir/TileMapper.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /terraForce/coding/snes/CodeGen/tileMapper/TileMapper.cpp -o CMakeFiles/TileMapper.dir/TileMapper.cpp.s

# Object files for target TileMapper
TileMapper_OBJECTS = \
"CMakeFiles/TileMapper.dir/TileMapper.cpp.o"

# External object files for target TileMapper
TileMapper_EXTERNAL_OBJECTS =

TileMapper: CMakeFiles/TileMapper.dir/TileMapper.cpp.o
TileMapper: CMakeFiles/TileMapper.dir/build.make
TileMapper: CMakeFiles/TileMapper.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/terraForce/coding/snes/CodeGen/tileMapper/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable TileMapper"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/TileMapper.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/TileMapper.dir/build: TileMapper

.PHONY : CMakeFiles/TileMapper.dir/build

CMakeFiles/TileMapper.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/TileMapper.dir/cmake_clean.cmake
.PHONY : CMakeFiles/TileMapper.dir/clean

CMakeFiles/TileMapper.dir/depend:
	cd /terraForce/coding/snes/CodeGen/tileMapper/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /terraForce/coding/snes/CodeGen/tileMapper /terraForce/coding/snes/CodeGen/tileMapper /terraForce/coding/snes/CodeGen/tileMapper/build /terraForce/coding/snes/CodeGen/tileMapper/build /terraForce/coding/snes/CodeGen/tileMapper/build/CMakeFiles/TileMapper.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/TileMapper.dir/depend

