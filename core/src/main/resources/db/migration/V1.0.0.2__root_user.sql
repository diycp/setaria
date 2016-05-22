--
-- Copyright (C) 2016 The Weghst Inc. <kevinz@weghst.com>
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--         http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

INSERT INTO t_user(email,password,type,createdTime) VALUES('root@weghst.com', md5('root'), 'manager', unix_timestamp() * 1000);