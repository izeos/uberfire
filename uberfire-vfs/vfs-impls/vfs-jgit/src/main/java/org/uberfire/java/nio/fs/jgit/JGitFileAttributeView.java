/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.java.nio.fs.jgit;

import org.uberfire.java.nio.IOException;
import org.uberfire.java.nio.file.attribute.BasicFileAttributeView;
import org.uberfire.java.nio.file.attribute.BasicFileAttributes;
import org.uberfire.java.nio.file.attribute.FileTime;

import com.gitblit.models.PathModel;

public class JGitFileAttributeView implements BasicFileAttributeView {

    private final PathModel pathModel;

    public JGitFileAttributeView(PathModel pathModel) {
        this.pathModel = pathModel;
    }

    @Override
    public BasicFileAttributes readAttributes() throws IOException {
        BasicFileAttributes attrs = new JGitlFileAttributes(pathModel);        
        return attrs;
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
    }

    @Override
    public String name() {
        return "basic";
    }
}